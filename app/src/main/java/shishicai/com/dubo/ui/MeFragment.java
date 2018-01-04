package shishicai.com.dubo.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shishicai.com.dubo.R;
import shishicai.com.dubo.WebActivity;
import shishicai.com.dubo.base.BaseFragment;

/**
 * 个人中心界面  用于检查更新，查看版本信息 已经推送中心
 */

public class MeFragment extends BaseFragment {

    RecyclerView rvToDoList;

    @Override
    protected void initView(View rootView) {

        rootView.findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebActivity.start(getActivity());
            }
        });

        rvToDoList = rootView.findViewById(R.id.rvToDoList);


        rvToDoList.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvToDoList.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(getActivity()).inflate(R.layout.item, null);
                return new RecyclerView.ViewHolder(view) {
                };
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });


    }

    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_me;
    }


}
