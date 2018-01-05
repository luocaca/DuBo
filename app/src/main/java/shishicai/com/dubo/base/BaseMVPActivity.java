package shishicai.com.dubo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * create by luocaca base mvp to use mvp partnner
 * at 2017/6/5
 */

public abstract class BaseMVPActivity extends AppCompatActivity{



    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        init();

        initView();
        initVH();
        initData();
        initListener();


    }

    protected void initListener() {

    }

    public void initData() {

    }

    protected void init() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(bindLayoutID());

    }

    public String setTitle() {
        return "";
    }

    public void setTitle(String tit) {
        if (title != null) {
            title.setText(tit);
        }
    }

    public abstract void initView();


    /**
     * 初始化viewHolder
     */
    public void initVH() {

    }




    public abstract int bindLayoutID();



//    /**
//     * Views indexed with their IDs
//     */
//    private SparseArray<View> views;
//
//    protected <T extends View> T getView(int viewId) {
//        View view = views.get(viewId);
//        if (view == null) {
//            view = findViewById(viewId);
//            views.put(viewId, view);
//        }
//        return (T) view;
//    }

}
