package net.lzzy.practicesonline.activities.framents;

import android.content.Context;
import android.view.View;

import net.lzzy.practicesonline.R;

import java.util.Calendar;

/**
 * Created by lzzy_mrp on 2019/4/10.
 * Description:
 */
public class SplashFragment extends BaseFragment {
    private int[] imgs = new int[]{R.drawable.splash1,R.drawable.splash2,R.drawable.splash3};
    private OnSplashFinishedListener listener;
    @Override
    protected void populate() {
        View wall = find(R.id.fragment_splash_wall);
        int pos = Calendar.getInstance().get(Calendar.SECOND) % 3;
        wall.setBackgroundResource(imgs[pos]);
        wall.setOnClickListener(v -> listener.cancelCount());
    }

    @Override
    public int getLayoutRes() {
        return R.layout.frament_splash;
    }

    @Override
    public void search(String kw) {

    }

    @Override
    public void handlePracticeExtion(String message) {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSplashFinishedListener){
            listener = (OnSplashFinishedListener) context;
        }else {
            throw new ClassCastException(context.toString()+"必须实现OnSplashFinishedListener");
        }
    }

    public interface OnSplashFinishedListener{
        void cancelCount();
    }

}
