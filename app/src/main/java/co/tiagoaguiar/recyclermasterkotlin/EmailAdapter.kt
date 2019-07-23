package co.tiagoaguiar.recyclermasterkotlin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.NORMAL
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.util.isNotEmpty
import androidx.core.view.ViewCompat.animate
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.recyclermasterkotlin.model.Email
import kotlinx.android.synthetic.main.email_item.view.*

/**
 *
 * Julho, 08 2019
 * @author suporte@moonjava.com.br (Tiago Aguiar).
 */
class EmailAdapter(val emails: MutableList<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    val selectedItems = SparseBooleanArray()
    private var currentSelectedPos: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.email_item, parent, false)
        return EmailViewHolder(view)
    }

    override fun getItemCount(): Int = emails.size

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.bind(emails[position])
        holder.itemView.setOnClickListener {
            if (selectedItems.isNotEmpty())
                onItemClick?.invoke(position)
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClick?.invoke(position)
            return@setOnLongClickListener true
        }

        if (currentSelectedPos == position) currentSelectedPos = -1
    }

    fun toggleSelection(position: Int) {
        currentSelectedPos = position
        if (selectedItems[position, false]) {
            selectedItems.delete(position)
            emails[position].selected = false
        } else {
            selectedItems.put(position, true)
            emails[position].selected = true
        }

        notifyItemChanged(position)
    }

    fun deleteEmails() {
        emails.removeAll(
            emails.filter { it.selected }
        )
        notifyDataSetChanged()
        currentSelectedPos = -1
    }

    var onItemClick: ((Int) -> Unit)? = null
    var onItemLongClick: ((Int) -> Unit)? = null

    inner class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(email: Email) {
            with(email) {
                val hash = user.hashCode()
                itemView.txt_icon.text = user.first().toString()
                itemView.txt_icon.background = itemView.oval(Color.rgb(hash, hash / 2, 0))
                itemView.txt_user.text = user
                itemView.txt_subject.text = subject
                itemView.txt_preview.text = preview
                itemView.txt_date.text = date

                itemView.txt_user.setTypeface(Typeface.DEFAULT, if (unread) BOLD else NORMAL)
                itemView.txt_subject.setTypeface(Typeface.DEFAULT, if (unread) BOLD else NORMAL)
                itemView.txt_date.setTypeface(Typeface.DEFAULT, if (unread) BOLD else NORMAL)

                itemView.img_star.setImageResource(
                    if (stared) R.drawable.ic_star_black_24dp
                    else R.drawable.ic_star_border_black_24dp
                )

                if (email.selected) {
                    itemView.txt_icon.background = itemView.txt_icon.oval(
                        Color.rgb(26,115,233)
                    )
                    itemView.background = GradientDrawable().apply {
                        shape = GradientDrawable.RECTANGLE
                        cornerRadius = 32f
                        setColor(Color.rgb(232, 240, 253))
                    }
                } else {
                    itemView.background = GradientDrawable().apply {
                        shape = GradientDrawable.RECTANGLE
                        cornerRadius = 32f
                        setColor(Color.WHITE)
                    }
                }

                 if (selectedItems.isNotEmpty())
                     animate(itemView.txt_icon, email)
            }
        }

    }

    private fun animate(view: TextView, email: Email) {
        val oa1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f)
        val oa2 = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)

        oa1.interpolator = DecelerateInterpolator()
        oa2.interpolator = AccelerateDecelerateInterpolator()

        oa1.duration = 200
        oa2.duration = 200

        oa1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (email.selected)
                    view.text = "\u2713"
                oa2.start()
            }
        })
        oa1.start()
    }

}

fun View.oval(@ColorInt color: Int): ShapeDrawable {
    val oval = ShapeDrawable(OvalShape())
    with(oval) {
        intrinsicHeight = height
        intrinsicWidth = width
        paint.color = color
    }
    return oval
}