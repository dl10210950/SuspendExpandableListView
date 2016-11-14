package com.duanlian.suspendexpandablelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.duanlian.suspendexpandablelistview.adapter.MyAdapter;
import com.duanlian.suspendexpandablelistview.veiw.PinnedHeaderExpandableListView;

public class MainActivity extends AppCompatActivity {
    PinnedHeaderExpandableListView mExpandableListView;
    private MyAdapter mAdapter;
    private String[] groupData;
    private String[][] childrenData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    private void initView() {
        mExpandableListView = (PinnedHeaderExpandableListView) findViewById(R.id.expand_listview);
    }

    private void initData() {
        groupData = new String[10];
        childrenData = new String[10][10];
        for(int i=0;i<10;i++){
            groupData[i] = "分组"+i;
        }

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                childrenData[i][j] = "好友"+i+"-"+j;
            }
        }
        //设置悬浮头部VIEW
        mExpandableListView.setHeaderView(getLayoutInflater().inflate(R.layout.group,
                mExpandableListView, false));
        mAdapter = new MyAdapter(this,groupData,childrenData, mExpandableListView);
//        explistview.setAdapter(adapter);
//        mAdapter = new MyAdapter(this,groupData,childrenData);
        mExpandableListView.setAdapter(mAdapter);
        //将所有项设置成默认展开
        int groupCount = mExpandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            mExpandableListView.expandGroup(i);
        }
    }
}
