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

import com.onehundredcentury.liuhaizi.R;

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
	
	public static Dialog createChoosePhotoDialog(final Context c,
			final ChoosePhoteDialogCallback callback) {
		final Dialog dialog = new Dialog(c, R.style.MyDialog1);
		Window mChoosePhotoView = dialog.getWindow();
		mChoosePhotoView.setContentView(R.layout.choose_avatar);
		Button albumButton = (Button) mChoosePhotoView
				.findViewById(R.id.choose_album);
		Button camButton = (Button) mChoosePhotoView
				.findViewById(R.id.choose_cam);
		Button cancelButton = (Button) mChoosePhotoView
				.findViewById(R.id.choose_cancel);
		albumButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				callback.onOpenAlbum();
				// 从相册中去获取
			}

		});

		camButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				callback.onOpenCamera();
			}

		});

		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}

		});

		return dialog;
	}
	
	public static Dialog createLoadingDialog(Context context) {
		final Dialog dialog = new Dialog(context, R.style.MyDialog1);
		Window loadingWindow = dialog.getWindow();
		loadingWindow.setContentView(R.layout.dialog_loading_circle);
		ImageView loadingImage =(ImageView)loadingWindow.findViewById(R.id.loadingImage);
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_loading_circle);
		loadingImage.startAnimation(animation);
		return dialog;
	}

	public static ProgressDialog createProgressDialog(Context context, String title, String desc) {
		ProgressDialog pDialog = new ProgressDialog(context);
		//设置对话框标题
		pDialog.setTitle(title);
		//设置对话框显示的提示信息
		pDialog.setMessage(desc);
		//不允许通过返回键关闭对话框
		pDialog.setCancelable(false);
		//设置进度条类型为水平
		pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pDialog.setMax(100);  
		// 设置对话框的进度条是否是不确定的
		pDialog.setIndeterminate(false);
		return pDialog;
	}
	
	public interface ChoosePhoteDialogCallback {
		void onOpenAlbum();
		void onOpenCamera();
	}
}
