package com.deitel.flagquiz;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.view.View;
        import android.widget.RelativeLayout;

        import java.io.File;
        import java.util.ArrayList;
import android.widget.Button;
/**
 * 项目名称：ShareDome
 * 类描述：
 * 创建人：xiaolijuan
 * 创建时间：2016/1/13 23:48
 */public class ShareActivity extends Activity {
    private Button btnshareText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        btnshareText = (Button) findViewById(R.id.btnshareText);
        btnshareText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND); // 启动分享发送的属性
                intent.setType("text/plain"); // 分享发送的数据类型
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject"); // 分享的主题
                intent.putExtra(Intent.EXTRA_TEXT, "extratext"); // 分享的内容
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 这个也许是分享列表的背景吧
                ShareActivity.this.startActivity(Intent.createChooser(
                        intent, "分享"));// 目标应用选择对话框的标题
            }
        });
    }

    //分享文字
    public void shareText(View view) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my Share text.");
        shareIntent.setType("text/plain");

        //设置分享列表的标题，并且每次都显示分享列表
        startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

    //分享单张图片
    public void shareSingleImage(View view) {
        String imagePath = Environment.getExternalStorageDirectory() + File.separator + "test.jpg";
        //由文件得到uri
        Uri imageUri = Uri.fromFile(new File(imagePath));
        Log.d("share", "uri:" + imageUri);  //输出：file:///storage/emulated/0/test.jpg

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

    //分享多张图片
    public void shareMultipleImage(View view) {
        ArrayList<Uri> uriList = new ArrayList<>();

        String path = Environment.getExternalStorageDirectory() + File.separator;
        uriList.add(Uri.fromFile(new File(path+"australia_1.jpg")));
        uriList.add(Uri.fromFile(new File(path+"australia_2.jpg")));
        uriList.add(Uri.fromFile(new File(path+"australia_3.jpg")));

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "分享到"));
    }
}


