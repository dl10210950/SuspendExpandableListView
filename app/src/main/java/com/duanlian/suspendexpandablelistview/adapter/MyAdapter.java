package com.duanlian.suspendexpandablelistview.adapter;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.duanlian.suspendexpandablelistview.MainActivity;
import com.duanlian.suspendexpandablelistview.R;
import com.duanlian.suspendexpandablelistview.veiw.PinnedHeaderExpandableListView;

/**
 * adapter
 *
 */

public class MyAdapter extends BaseExpandableListAdapter implements PinnedHeaderExpandableListView.HeaderAdapter {
    private Context mContext;
    private String[] groupData;
    private String[][] childrenData;
    private PinnedHeaderExpandableListView mExpandableListView;
    public MyAdapter(MainActivity mainActivity, String[] groupData, String[][] childrenData,PinnedHeaderExpandableListView mExpandableListView) {
        this.mContext = mainActivity;
        this.groupData = groupData;
        this.childrenData = childrenData;
        this.mExpandableListView = mExpandableListView;
    }



    @Override
    public int getGroupCount() {
        return groupData.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childrenData[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childrenData[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.group, null);
        TextView textView = (TextView) view.findViewById(R.id.group_textview);
        textView.setText(groupData[groupPosition]);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.child, null);
        TextView textView = (TextView) view.findViewById(R.id.child_textview);
        textView.setText(childrenData[groupPosition][childPosition]);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public int getHeaderState(int groupPosition, int childPosition) {
        final int childCount = getChildrenCount(groupPosition);
        if (childPosition == childCount - 1) {
            return PINNED_HEADER_PUSHED_UP;
        } else if (childPosition == -1
                && !mExpandableListView.isGroupExpanded(groupPosition)) {
            return PINNED_HEADER_GONE;
        } else {
            return PINNED_HEADER_VISIBLE;
        }
    }

    /**
     * 设置悬浮头的数据,一般和group里面的一样
     * @param header
     * @param groupPosition
     * @param childPosition
     * @param alpha
     */
    @Override
    public void configureHeader(View header, int groupPosition, int childPosition, int alpha) {
        String groupData =  this.groupData[groupPosition];
        ((TextView) header.findViewById(R.id.group_textview)).setText(groupData);

    }
    private SparseIntArray groupStatusMap = new SparseIntArray();
    @Override
    public void setGroupClickStatus(int groupPosition, int status) {
        groupStatusMap.put(groupPosition, status);
    }

    @Override
    public int getGroupClickStatus(int groupPosition) {
        if (groupStatusMap.keyAt(groupPosition)>=0) {
            return groupStatusMap.get(groupPosition);
        } else {
            return 0;
        }
    }
}
