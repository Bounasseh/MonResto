// Generated code from Butter Knife. Do not modify!
package com.example.monresto.activities;

import android.view.View;
import android.widget.GridView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.monresto.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MenuActivity_ViewBinding implements Unbinder {
  private MenuActivity target;

  @UiThread
  public MenuActivity_ViewBinding(MenuActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MenuActivity_ViewBinding(MenuActivity target, View source) {
    this.target = target;

    target.gridView = Utils.findRequiredViewAsType(source, R.id.activity_menu_gridView, "field 'gridView'", GridView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MenuActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.gridView = null;
  }
}
