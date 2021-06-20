package com.websarva.wings.android.listviewsample2

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class OrderConfirmDailogFragment : DialogFragment() {

    // processing for generate Dialog Object.
    // procedure:
    // 1.generate Builder
    // 2.config display
    // 3.config ActionButton
    // 4.generate DialogObject
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // if activity is not null, generate Dialog Object.
        // SafeCallOperator(?) & let func -> nullCheck target is replace with it.
        // described variable in end is to be returnValue.
        val dialog = activity?.let {
            // 1.generate AlertDialog.Builder Object
            val builder = AlertDialog.Builder(it)

            // 2.config Display
            // config title of Dialog
            builder.setTitle(R.string.dialog_title)
            // config message of Dialog
            builder.setMessage(R.string.dialog_msg)

            // 3.config ActionButton
            // second argment:ListenerClass when tapped Button
            // config Positive Button
            builder.setPositiveButton(R.string.dialog_btn_ok, DialogButtonClickListener())
            // config Negative Button
            builder.setNegativeButton(R.string.dialog_btn_ng, DialogButtonClickListener())
            // config Neutral Button
            builder.setNeutralButton(R.string.dialog_btn_nu, DialogButtonClickListener())

            // 4.generate Dialog Object
            // ReturnValue in Let Block
            builder.create()
        }
        // return generated DIalog Object
        // Elvis operator(?:) is processing in the case of NULL.
        return dialog?:throw IllegalStateException("Activity is null")

    }

    // MemberClass : process when tapped ActionButton of Dialog
    // どのボタンも同じリスナクラスを用意し、リスナクラス内でwhitchで分岐処理をするのが一番効率が良い
    private inner class DialogButtonClickListener:DialogInterface.OnClickListener {

        override fun onClick(dialog:DialogInterface, which: Int) {

            // prepare variable for ToastMessage
            var msg = ""
            // conditional branch by tapped ActionButton
            when(which) {
                // case PositiveButton
                DialogInterface.BUTTON_POSITIVE ->
                    // store message for order
                    msg = getString(R.string.dialog_ok_toast)
                // case NegativeButton
                DialogInterface.BUTTON_NEGATIVE ->
                    // store message for cancel
                    msg = getString(R.string.dialog_ng_toast)
                // case NeutralButton
                DialogInterface.BUTTON_NEUTRAL ->
                    // store message for inquiry
                    msg = getString(R.string.dialog_nu_toast)
            }
            // display toast
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()

        }

    }
}