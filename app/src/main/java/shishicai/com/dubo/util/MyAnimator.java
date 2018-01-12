package shishicai.com.dubo.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class MyAnimator {

    public static int lastAnimatedPosition = -1;
    public static boolean animationsLocked = false;

    public static void runEnterAnimation(View view, int position) {


        if (animationsLocked) return;              //animationsLocked是布尔类型变量，一开始为false
        //确保仅屏幕一开始能够容纳显示的item项才开启动画

        if (position > lastAnimatedPosition) {//lastAnimatedPosition是int类型变量，默认-1，
            lastAnimatedPosition = position;


            view.setTranslationY(50);     //Item项一开始相对于原始位置下方500距离
            view.setAlpha(1f);           //item项一开始完全透明
            //每个item项两个动画，从透明到不透明，从下方移动到原始位置


            view.animate()
                    .translationY(0.8f).alpha(1.f)                                //设置最终效果为完全不透明
                    //并且在原来的位置
//                    .setStartDelay(20 * (position))//根据item的位置设置延迟时间
                    //达到依次动画一个接一个进行的效果
                    .setInterpolator(new DecelerateInterpolator(0.5f))     //设置动画位移先快后慢的效果
                    .setDuration(400)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animationsLocked = true;
                            //确保仅屏幕一开始能够显示的item项才开启动画
                            //也就是说屏幕下方还没有显示的item项滑动时是没有动画效果
                            //确保仅屏幕一开始能够显示的item项才开启动画
                            //也就是说屏幕下方还没有显示的item项滑动时是没有动画效果
                        }
                    })
                    .start();

        }
    }

}
