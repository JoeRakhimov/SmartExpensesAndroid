package com.joerakhimov.smartexpenses.screen.main.profile


import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.ipakyulibank.mobile.util.permissions.MyPermissionChecker
import com.ipakyulibank.mobile.util.permissions.MyPermissionListener
import com.joerakhimov.smartexpenses.R
import com.joerakhimov.smartexpenses.base.BaseFragment
import com.joerakhimov.smartexpenses.databinding.FragmentProfileBinding
import com.joerakhimov.smartexpenses.di.Injector
import com.joerakhimov.smartexpenses.extension.startActivityToOpenUrlInBrowser
import com.joerakhimov.smartexpenses.helper.transformation.CircleTransform
import com.joerakhimov.smartexpenses.screen.auth.AuthActivity
import com.joerakhimov.smartexpenses.screen.main.profile.model.ProfileInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

const val REQUEST_CODE_PICK_IMAGE = 1

class ProfileFragment : BaseFragment() {

    override fun getLayoutRes() = com.joerakhimov.smartexpenses.R.layout.fragment_profile

    private lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var permissionChecker: MyPermissionChecker

    init {
        Injector.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initProfileImage()
        observeToastMessage()
        observeIsOpen()
        observeProfileInfo()
    }

    private fun initProfileImage() {
        Picasso.get().load(R.drawable.profile)
            .transform(CircleTransform()).into(image_profile)
        image_profile.setOnClickListener {
            permissionChecker.checkWriteExternalStoragePermission(activity, object : MyPermissionListener {
                override fun onAllow() {
                    dispatchImagePickIntent()
                }
                override fun onDeny() {}
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.getRoot()
    }

    private fun observeToastMessage() {
        viewModel.toastMessage.observe(this, Observer { any ->
            var message = ""
            when (any) {
                is Int -> message = getString(any)
                is String -> message = any
            }
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        })
    }

    private fun observeProfileInfo(){
        viewModel.profileInfo.observe(this, Observer {
            if(it!=null) initProfileSettings(it)
        })
    }

    private fun initProfileSettings(profileInfo: ProfileInfo) {
        recycler_profile_settings.layoutManager = LinearLayoutManager(context)

        val items = arrayListOf(
            ProfileScreenListItem(com.joerakhimov.smartexpenses.R.string.notifications),
            ProfileScreenListItem(com.joerakhimov.smartexpenses.R.string.number_of_latest_spendings),
            ProfileScreenListItem(com.joerakhimov.smartexpenses.R.string.set_profile_color, View.OnClickListener {
                if(profileInfo.color!=null) showColorPickerDialog(profileInfo.color)
            }),
            ProfileScreenListItem(
                com.joerakhimov.smartexpenses.R.string.privacy,
                View.OnClickListener { context?.startActivityToOpenUrlInBrowser(profileInfo.privacyUrl) }),
            ProfileScreenListItem(
                com.joerakhimov.smartexpenses.R.string.terms_and_conditions,
                View.OnClickListener { context?.startActivityToOpenUrlInBrowser(profileInfo.termsAndConditionsUrl) }
            )
        )

        recycler_profile_settings.adapter = ProfileSettingsAdapter(items)
    }

    private fun showColorPickerDialog(hex: String) {
        ColorPickerDialogBuilder
            .with(context)
            .setTitle(R.string.choose_profile_color)
            .initialColor(Integer.decode(hex))
            .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
            .density(12)
            .setPositiveButton(R.string.save) { dialog, selectedColor, allColors ->
                viewModel.onColorSelection("#"+Integer.toHexString(selectedColor))
            }
            .setNegativeButton(R.string.cancel) { dialog, which -> }
            .build()
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.joerakhimov.smartexpenses.R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.joerakhimov.smartexpenses.R.id.logout -> viewModel.onLogoutButtonClick()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeIsOpen() {
        viewModel.isOpen.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                startActivity(Intent(activity, AuthActivity::class.java))
            }
        })
    }

    private fun dispatchImagePickIntent() {
        //Create an Intent with action as ACTION_PICK
        val intent = Intent(Intent.ACTION_PICK)
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.type = "image/*"
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        // Launching the Intent
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if(REQUEST_CODE_PICK_IMAGE == requestCode){
                val selectedImage = data?.data
                Picasso.get().load(selectedImage).transform(CircleTransform()).into(image_profile)
//                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
//                // Get the cursor
//                val cursor = activity?.contentResolver?.query(selectedImage, filePathColumn, null, null, null)
//                // Move to first row
//                cursor?.moveToFirst()
//                //Get the column index of MediaStore.Images.Media.DATA
//                val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
//                //Gets the String value in the column
//                val imgDecodableString = cursor?.getString(columnIndex!!)
//
//                if(imgDecodableString!=null) setPic(imgDecodableString, image_profile)
//
//                cursor?.close()
            }
        }
    }

    private fun setPic(path: String, imageView: ImageView) {
        imageView.post {
            // Get the dimensions of the View
            val targetW: Int = imageView.width
            val targetH: Int = imageView.height

            val bmOptions = BitmapFactory.Options().apply {
                // Get the dimensions of the bitmap
                inJustDecodeBounds = true
                BitmapFactory.decodeFile(path, this)
                val photoW: Int = outWidth
                val photoH: Int = outHeight

                // Determine how much to scale down the image
                val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

                // Decode the image file into a Bitmap sized to fill the View
                inJustDecodeBounds = false
                inSampleSize = scaleFactor
                inPurgeable = true
            }
            BitmapFactory.decodeFile(path, bmOptions)?.also { bitmap ->
                imageView.setImageBitmap(bitmap)
            }
        }
    }

}
