package com.swensonhe.test.ui.main

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.swensonhe.test.R
import com.swensonhe.test.databinding.ItemImageBinding
import com.swensonhe.test.databinding.ItemTextBinding
import com.swensonhe.test.model.Item

class ItemsAdapter(private var items: ArrayList<Item>, private var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = items.size


    inner class ItemWithImageViewHolder(
        private val itemImageBinding: ItemImageBinding,
        private val viewType: Int
    ) :
        RecyclerView.ViewHolder(itemImageBinding.root) {
        fun bind(item: Item) {
            when (viewType) {
                2 ->
                    Glide
                        .with(context)
                        .load(item.value)
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher)
                        .into(itemImageBinding.image)

                3 -> {
                    initCode(item.value,true)
                }

                4 ->
                    initCode(item.value,false)

            }

        }

        private fun initCode(value: String, isQr: Boolean = true) {
            val mWriter = MultiFormatWriter();
            try {
                val mMatrix: BitMatrix =
                    mWriter.encode(
                        value,
                        if (isQr) BarcodeFormat.QR_CODE else BarcodeFormat.CODE_128, 400, 400
                    );
                val mEncoder = BarcodeEncoder()
                val mBitmap: Bitmap =
                    mEncoder.createBitmap(mMatrix)
                itemImageBinding.image.setImageBitmap(mBitmap)

            } catch (e: WriterException) {
                e.printStackTrace();
            }
        }
    }


    inner class ItemWithoutImageViewHolder(private val itemTextBinding: ItemTextBinding) :
        RecyclerView.ViewHolder(itemTextBinding.root) {
        fun bind(item: Item) {
            itemTextBinding.text.text = item.value
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val view =
                ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemWithoutImageViewHolder(view)
        } else {
            val view =
                ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemWithImageViewHolder(view, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1)
            (holder as ItemWithoutImageViewHolder).bind(items[position])
        else
            (holder as ItemWithImageViewHolder).bind(items[position])

    }
}
