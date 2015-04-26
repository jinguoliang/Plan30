package com.example.jinux.thirtydays.common;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jinux.thirtydays.R;


public class DialogUtil {
	/**
	 * @param c
	 *            Context
	 * @param title
	 *            弹出框标题,可为空
	 * @param content
	 *            内容,可为空
	 * @param leftButtonText
	 *            左边button的显示文本,若为空则没有此按钮
	 * @param rightButtonText
	 *            右边button的显示文本,若为空则没有此按钮
	 * @param leftClickListener
	 *            左边button的监听,若为空则没有此按钮
	 * @param rightClickListener
	 *            右边button的监听,若为空则没有此按钮
	 */
	public static Dialog createOkCancelDialog(Context c, String title,
			String content, String leftButtonText, String rightButtonText,
			OnClickListener leftClickListener,
			OnClickListener rightClickListener) {
		Dialog dialog = new Dialog(c, R.style.MyDialog);
		Window diaWindow = dialog.getWindow();
		diaWindow.setContentView(R.layout.dialog_ok_cancel);
		TextView tvTitle = (TextView) diaWindow.findViewById(R.id.dialog_title);
		TextView tvContent = (TextView) diaWindow
				.findViewById(R.id.dialog_content);
		TextView btnLeft = (TextView) diaWindow
				.findViewById(R.id.dialog_left_button);
		TextView btnRight = (TextView) diaWindow
				.findViewById(R.id.dialog_right_button);
		View verticalLine = diaWindow.findViewById(R.id.vertical_line);
		View horizontalLine = diaWindow.findViewById(R.id.vertical_line);
		View layoutButtons = diaWindow.findViewById(R.id.layout_dialog_buttons);
		if (TextUtils.isEmpty(title)) {
			tvTitle.setVisibility(View.GONE);
		} else {
			tvTitle.setText(title);
		}

		if (TextUtils.isEmpty(content)) {
			tvContent.setVisibility(View.GONE);
		} else {
			tvContent.setText(content);
		}
		boolean hasLeft = true;
		if (TextUtils.isEmpty(leftButtonText) || leftClickListener == null) {
			btnLeft.setVisibility(View.GONE);
			verticalLine.setVisibility(View.GONE);
			hasLeft = false;
			btnRight.setBackgroundResource(R.drawable.dialog_button_bg);
		} else {
			btnLeft.setOnClickListener(leftClickListener);
			btnLeft.setText(leftButtonText);
		}
		if (TextUtils.isEmpty(rightButtonText) || rightClickListener == null) {
			btnRight.setVisibility(View.GONE);
			verticalLine.setVisibility(View.GONE);
			if (!hasLeft) {
				horizontalLine.setVisibility(View.GONE);
				layoutButtons.setVisibility(View.GONE);
			}
			btnLeft.setBackgroundResource(R.drawable.dialog_button_bg);
		} else {
			btnRight.setOnClickListener(rightClickListener);
			btnRight.setText(rightButtonText);
		}
		return dialog;
	}

	public static Dialog createOkCancelDialog(Context context, String content, OnClickListener leftClickListener,
			OnClickListener rightClickListener) {
		return createOkCancelDialog(context, context.getString(R.string.dialog_title), content, context.getString(R.string.cancel), context.getString(R.string.sure), leftClickListener, rightClickListener);
	}
}
