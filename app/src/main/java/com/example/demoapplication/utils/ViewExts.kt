package com.example.demoapplication.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.example.demoapplication.R
import com.example.demoapplication.utils.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern


fun View.isViewHide(): Boolean {
    return this.visibility == View.GONE
}


fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.showOnlyWhen(isShow: Boolean) {
    if (isShow)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}

fun View.hideKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun View?.showSnackBar(message: String?, icon: Int = 0) {
    if (this != null && message.checkNotEmpty()) {
        Snackbar.make(this, message!!, Snackbar.LENGTH_SHORT).apply {
            addIconIfNeeded(icon)
            show()
        }
    }
}

fun String?.checkNotEmpty(): Boolean {
    return this != null && isNotEmpty() && isNotBlank()
}





fun View?.showSnackBar(message: Int, icon: Int = 0) {
    this ?: return

    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).apply {
        addIconIfNeeded(icon)
        show()
    }
}

fun Snackbar.addIconIfNeeded(icon: Int = 0) {
    if (icon != 0) {
        val snackBarLayout = view
        val textView =
            snackBarLayout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.gravity = Gravity.CENTER_VERTICAL
        textView.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
        textView.compoundDrawablePadding = 10
    }
}

inline fun <reified T : Any> Context.launchActivity(
    removeFromStack: Boolean = false,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {},
) {

    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivity(intent, options)
    } else {
        startActivity(intent)
    }
}

inline fun <reified T : Any> Activity.launchActivityWithFinish(
    removeFromStack: Boolean = false,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {},
) {

    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivity(intent, options)
    } else {
        startActivity(intent)
    }
    this.finish()
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)


fun String.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidUserName(): Boolean {
    // Regex to check valid username.
    val regex = "^[A-Za-z]\\w{2,29}$"

    // Compile the ReGex
    val p = Pattern.compile(regex)

    // If the username is empty
    // return false
    if (this == null) {
        return false
    }

    // Pattern class contains matcher() method
    // to find matching between given username
    // and regular expression.
    val m = p.matcher(this)

    // Return if the username
    // matched the ReGex
    return m.matches()
}


/**
 * Check Mobile number is valid or not
 */
fun String.isValidMobile(): Boolean {
    return Patterns.PHONE.matcher(this).matches()
}

fun String.IsValidPassword(): Boolean {
    val pattern: Pattern
//    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?])(?=\\S+$).{4,}$"
    val PASSWORD_PATTERN = "^(?=\\S+$).{6,}$"
    pattern = Pattern.compile(PASSWORD_PATTERN)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}

fun FragmentActivity.replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(containerViewId, fragment)
        .addToBackStack(null)
        .commit()
}

fun FragmentActivity.replaceFragmentAddToBackStack(
    @IdRes containerViewId: Int,
    fragment: Fragment,
) {
    supportFragmentManager
        .beginTransaction()
        .replace(containerViewId, fragment)
        .addToBackStack(fragment.tag)
        .commit()
}

fun FragmentActivity.replaceFragment(
    @IdRes containerViewId: Int,
    fragment: Fragment,
    isFirst: Boolean = false,
) {
    if (isFirst) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment)
            .commit()
    } else {
        supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment)
            .addToBackStack(null)
            .commit()
    }
}

fun Context.showDialog(
    msg: String,
    title: String? = resources.getString(R.string.app_name),
    positiveText: String? = "OK",
    listener: DialogInterface.OnClickListener? = null,
    negativeText: String? = "Cancel",
    negativeListener: DialogInterface.OnClickListener? = null,
    icon: Int? = null,
    isCancelDialog: Boolean = false,
) {
    if (BaseActivity.dialogShowing) {
        return
    }
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(msg)
    builder.setCancelable(isCancelDialog)
    builder.setPositiveButton(positiveText) { dialog, which ->
        BaseActivity.dialogShowing = false
        listener?.onClick(dialog, which)
    }
    if (negativeListener != null) {
        builder.setNegativeButton(negativeText) { dialog, which ->
            BaseActivity.dialogShowing = false
            negativeListener.onClick(dialog, which)
        }
    }
    if (icon != null) {
        builder.setIcon(icon)
    }
    val dialog = builder.create()
    dialog.show()
    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
    BaseActivity.dialogShowing = true
}

fun AppCompatImageView.loadImage(url: String) {
    this.let {
        Glide.with(it)
            .load(url)
            .into(it)
    }

}
fun AppCompatImageView.loadDrawableImage(res: Int) {
    this.let {
        Glide.with(it)
            .load(res)
            .into(it)
    }

}

fun AppCompatImageView.loadMarketGif(res: Int) {
    this.let {
        Glide.with(context)
            .load(res)
            .fitCenter()
            .into(it)
    }

}






fun AppCompatImageView.loadImageWithOutPlaceholder(res: String) {
    this.let {
        Glide.with(it)
            .load(res)
            .fitCenter()
            .into(it)

    }
}

fun AppCompatImageView.loadImageWithOutPlaceholder(res: Int) {
    this.let {
        Glide.with(it)
            .load(res)
            .fitCenter()
            .into(it)

    }
}

fun AppCompatImageView.loadImageRes(res: Int) {
    this.let {
        Glide.with(it)
            .load(res)
            .into(it)

    }

}


fun AppCompatImageView.loadRoundedImage(res: Int) {
    this.let {
        Glide.with(it)
            .load(res)
            .circleCrop()
            .into(it)
    }
}


fun ImageView.loadRoundedImage(imagePath: String) {
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))

    this.let {
        Glide.with(it)
            .load(imagePath)
            .centerCrop()
            .apply(requestOptions)
            .into(it)
    }
}

fun ImageView.loadSqareImage(imagePath: String) {
    this.let {
        Glide.with(it)
            .load(imagePath)
            .fitCenter()
            .into(it)
    }
}

fun AppCompatImageView.loadRoundedImageNew(imageRes: Int) {
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))

    this.let {
        Glide.with(it)
            .load(imageRes)
            .centerCrop()
            .apply(requestOptions)
            .into(it)
    }
}

fun <T> ImageView.loadCircularImage(
    model: T,
    borderSize: Float = 0F,
    borderColor: Int = Color.WHITE,
) {
    Glide.with(context)
        .asBitmap()
        .load(model)
        .apply(RequestOptions.circleCropTransform())
        .into(object : BitmapImageViewTarget(this) {
            override fun setResource(resource: Bitmap?) {
                setImageDrawable(
                    resource?.run {
                        RoundedBitmapDrawableFactory.create(
                            resources,
                            if (borderSize > 0) {
                                createBitmapWithBorder(borderSize, borderColor)
                            } else {
                                this
                            }
                        ).apply {
                            isCircular = true
                        }
                    }
                )
            }
        })
}

/**
 * Create a new bordered bitmap with the specified borderSize and borderColor
 *
 * @param borderSize - The border size in pixel
 * @param borderColor - The border color
 * @return A new bordered bitmap with the specified borderSize and borderColor
 */
private fun Bitmap.createBitmapWithBorder(borderSize: Float, borderColor: Int): Bitmap {
    val borderOffset = (borderSize * 2).toInt()
    val halfWidth = width / 2
    val halfHeight = height / 2
    val circleRadius = Math.min(halfWidth, halfHeight).toFloat()
    val newBitmap = Bitmap.createBitmap(
        width + borderOffset,
        height + borderOffset,
        Bitmap.Config.ARGB_8888
    )

    // Center coordinates of the image
    val centerX = halfWidth + borderSize
    val centerY = halfHeight + borderSize

    val paint = Paint()
    val canvas = Canvas(newBitmap).apply {
        // Set transparent initial area
        drawARGB(0, 0, 0, 0)
    }

    // Draw the transparent initial area
    paint.isAntiAlias = true
    paint.style = Paint.Style.FILL
    canvas.drawCircle(centerX, centerY, circleRadius, paint)

    // Draw the image
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(this, borderSize, borderSize, paint)

    // Draw the createBitmapWithBorder
    paint.xfermode = null
    paint.style = Paint.Style.STROKE
    paint.color = borderColor
    paint.strokeWidth = borderSize
    canvas.drawCircle(centerX, centerY, circleRadius, paint)
    return newBitmap
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

