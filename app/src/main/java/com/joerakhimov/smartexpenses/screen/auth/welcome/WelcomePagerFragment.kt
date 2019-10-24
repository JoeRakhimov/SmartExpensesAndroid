package com.joerakhimov.smartexpenses.screen.auth.welcome


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.joerakhimov.smartexpenses.R
import kotlinx.android.synthetic.main.fragment_launcher.view.*

private const val ARG_TITLE = "title"
private const val ARG_SUBTITLE = "subtitle"
private const val ARG_IMAGE_RES_ID = "imageResId"

class LauncherFragment : Fragment() {

    private var mTitle: Int? = null
    private var mSubtitle: Int? = null
    private var mImageResId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mTitle = it.getInt(ARG_TITLE)
            mSubtitle = it.getInt(ARG_SUBTITLE)
            mImageResId = it.getInt(ARG_IMAGE_RES_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launcher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mTitle != null) view.textTitle.setText(mTitle!!)
        if (mSubtitle != null) view.textSubtitle.setText(mSubtitle!!)
        if (mImageResId != null) view.imageWelcome.setImageResource(mImageResId!!)
    }

    companion object {
        @JvmStatic
        fun newInstance(title: Int, subtitle: Int, imageResId: Int) =
            LauncherFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TITLE, title)
                    putInt(ARG_SUBTITLE, subtitle)
                    putInt(ARG_IMAGE_RES_ID, imageResId)
                }
            }
    }

}
