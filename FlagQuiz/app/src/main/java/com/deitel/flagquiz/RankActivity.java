// SettingsActivity.java
// Activity to display SettingsActivityFragment on a phone
package com.deitel.flagquiz;

import java.text.NumberFormat;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;

import com.deitel.flagquiz.data.DatabaseDescription;


public class RankActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    LoaderManager manager = null;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        manager = this.getLoaderManager();
        manager.initLoader(1000, null, callbacks);

    }
    private LoaderManager.LoaderCallbacks<Cursor> callbacks = new LoaderCallbacks<Cursor>() {
        int rank1=1;
        @Override
        public void onLoaderReset(Loader<Cursor> arg0) {
            // TODO Auto-generated method stub

        }
        NumberFormat nt = NumberFormat.getPercentInstance();

        //      完成对UI的数据提取，更新UI的操作
        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            ArrayList<String> al = new ArrayList<String>();
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                double phone = cursor.getDouble(cursor.getColumnIndex("phone"));
                al.add("正确率第"+rank1+"名");
                al.add(name+"正确率="+nt.format(phone/100));
                rank1++;
            }

            initData(al);
            initView();
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
            CursorLoader loader = new CursorLoader(RankActivity.this,
                    Uri.parse("content://com.deitel.flagquiz.data.MyContentProvider/person"), null, null,
                    null, "phone desc");
            return loader;
        }
    };
    private void initData(ArrayList<String> al) {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyAdapter(al);
    }
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
    }




}
