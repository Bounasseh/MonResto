// Generated code from Butter Knife. Do not modify!
package com.example.monresto.activities;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.monresto.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CategoriesActivity_ViewBinding implements Unbinder {
  private CategoriesActivity target;

  private View view7f080042;

  @UiThread
  public CategoriesActivity_ViewBinding(CategoriesActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CategoriesActivity_ViewBinding(final CategoriesActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.activity_categories_gridView, "field 'gridView' and method 'onCategoryItemClick'");
    target.gridView = Utils.castView(view, R.id.activity_categories_gridView, "field 'gridView'", GridView.class);
    view7f080042 = view;
    ((AdapterView<?>) view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> p0, View p1, int p2, long p3) {
        target.onCategoryItemClick(p1);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CategoriesActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.gridView = null;

    ((AdapterView<?>) view7f080042).setOnItemClickListener(null);
    view7f080042 = null;
  }
}
