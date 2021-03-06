package com.msc.modulehomepage.viewcardcell

import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.msc.libcommon.util.StringUtils
import com.msc.libcoremodel.datamodel.http.entities.AllRecData
import com.msc.libcommon.base.CommenDataCell
import com.msc.libcommon.util.DensityUtil
import com.msc.libcoremodel.datamodel.http.entities.CommonData
import com.msc.modulehomepage.viewcard.PictureFollowCardView

class PictureFollowCardViewCell : CommenDataCell<PictureFollowCardView, CommonData.CommonItemList>() {

    var drawableWidth =0
    var drawableHeight =0

    override fun bindView(view: PictureFollowCardView) {
        super.bindView(view)
        view.tvName!!.text = mData!!.data!!.content!!.data!!.owner!!.nickname
        view.tvDescription!!.text = mData!!.data!!.content!!.data!!.description
        view.tvDate!!.text = StringUtils.getStringDate(mData!!.data!!.content!!.data!!.updateTime)
        view.tvLikeNum!!.text = mData!!.data!!.content!!.data!!.consumption!!.collectionCount.toString()
        view.tvMessageNum!!.text = mData!!.data!!.content!!.data!!.consumption!!.replyCount.toString()
        Glide.with(view.context!!).load(mData!!.data!!.content!!.data!!.owner!!.avatar).into(view.ivAuthorCover!!)

        Glide.with(view.context!!)
                .load(mData!!.data!!.content!!.data!!.cover!!.feed)
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        drawableWidth = resource.intrinsicWidth
                        drawableHeight = resource.intrinsicHeight
                        view.ivCover!!.setImageDrawable(resource)

                        val resources = view.context.resources
                        val dm = resources.displayMetrics
                        val width = dm.widthPixels
                        val layoutParamsCardView: ConstraintLayout.LayoutParams = view.cardViewCover!!.layoutParams as ConstraintLayout.LayoutParams
                        layoutParamsCardView.width = width - DensityUtil.dp2px(view.context, 16f)
                        layoutParamsCardView.height = layoutParamsCardView.width * drawableHeight / drawableWidth
                        view.cardViewCover!!.layoutParams = layoutParamsCardView
                    }
                })

    }

}
