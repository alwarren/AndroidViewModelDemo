package demo.kotlin.vm.ui

import android.content.Context
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.widget.EditText
import demo.kotlin.vm.R

class TextInputDialog(private val context: Context, private var title: String?) {
    constructor(context: Context, resource: Int) : this(context, null) {
        this.title = context.getString(resource)
    }

    init {
        if (context !is OnTextResult)
            throw(Throwable(context.getString(R.string.text_input_dialog_error)))

        if (title.isNullOrEmpty())
            title = context.getString(R.string.text_input_dialog_title)
    }

    fun build(): AlertDialog {
        val builder = AlertDialog.Builder(context)
        val input = EditText(context)
        input.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle(title)
                .setView(input)
                .setPositiveButton(R.string.ok, { _, _ ->
                    (context as OnTextResult)
                            .onTextDialogResult(input.text.toString())
                })
                .setNegativeButton(R.string.cancel, { dialog, _ -> dialog.cancel() })

        return builder.create()
    }

    interface OnTextResult {
        fun onTextDialogResult(input: String)
    }

}