package dub.beforeloding2;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Bob on 2017/11/9.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SlidingTabView slidingTabView = new SlidingTabView(this);
        setContentView(slidingTabView);
    }
}
