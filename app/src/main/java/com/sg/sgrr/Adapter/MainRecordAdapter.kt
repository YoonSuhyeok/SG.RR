package com.sg.sgrr.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sg.sgrr.Activity.RecordActivity
import com.sg.sgrr.DBHelper.DBHelper
import com.sg.sgrr.Model.Record
import com.sg.sgrr.R
import com.sg.sgrr.Etc.Items.Companion.charImageArray
import com.sg.sgrr.Etc.Items.Companion.charProfileArray

class MainRecordAdapter(private val MainRecordList: ArrayList<Record>): RecyclerView.Adapter<MainRecordAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_record_list, parent, false)
        return Holder(view).apply {
            val animation: Animation = AnimationUtils.loadAnimation(parent.context, R.anim.bounce)
            view.setOnClickListener {
                view.startAnimation(animation)
                val intent = Intent(parent.context, RecordActivity::class.java)
                intent.putExtra("UserNickname", MainRecordList[adapterPosition].userName)
                intent.putExtra("UserNumber", MainRecordList[adapterPosition].userId)
                parent.context.startActivity(intent)
            }
            deleteButton.setOnClickListener {
                val blackDB = DBHelper(parent.context)
                blackDB.deleteRecord(MainRecordList[adapterPosition].userName)
                MainRecordList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.userName.text = MainRecordList[position].userName
        holder.rank.text = MainRecordList[position].rank
        holder.rankNumber.text = MainRecordList[position].rankNumber
        holder.rankNumber2.text = String.format("위 (상위 %s%s)", MainRecordList[position].rankPercent, '%')
        //holder.most1Text.text = String.format("%s위", MainRecordList[position].mostPercent1)

        holder.mainCharImage.setImageResource(charProfileArray[ MainRecordList[position].mostImage1 - 1 ])
        holder.most1.setImageResource(charImageArray[ MainRecordList[position].mostImage1 - 1 ])
        if(MainRecordList[position].mostImage2 == 0){
            holder.most2.visibility = View.INVISIBLE
            holder.most2Text.visibility = View.INVISIBLE
        } else {
            holder.most2.setImageResource(charImageArray[ MainRecordList[position].mostImage2 - 1 ])
            // holder.most2Text.text = String.format("%s위", MainRecordList[position].mostPercent2)
        }
        if(MainRecordList[position].mostImage3 == 0){
            holder.most3.visibility = View.INVISIBLE
            holder.most3Text.visibility = View.INVISIBLE
        } else {
            holder.most3.setImageResource(charImageArray[ MainRecordList[position].mostImage3 - 1 ])
            //holder.most3Text.text = String.format("%s위", MainRecordList[position].mostPercent3)
        }
    }

    override fun getItemCount(): Int {
        return MainRecordList.size
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var mainCharImage: ImageView   = itemView.findViewById(R.id.MainChar)
        var userName     : TextView    = itemView.findViewById(R.id.UserName)
        var rank         : TextView    = itemView.findViewById(R.id.rank)
        var rankNumber   : TextView    = itemView.findViewById(R.id.ranknumber)
        var rankNumber2  : TextView    = itemView.findViewById(R.id.ranknumber2)
        var most1        : ImageView   = itemView.findViewById(R.id.most1)
        var most1Text    : TextView    = itemView.findViewById(R.id.most1Text)
        var most2        : ImageView   = itemView.findViewById(R.id.most2)
        var most2Text    : TextView    = itemView.findViewById(R.id.most2Text)
        var most3        : ImageView   = itemView.findViewById(R.id.most3)
        var most3Text    : TextView    = itemView.findViewById(R.id.most3Text)
        val deleteButton : ImageButton = itemView.findViewById(R.id.ExitButton)
    }
}