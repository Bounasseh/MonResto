// Generated code from Butter Knife. Do not modify!
package com.example.monresto.activities;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.monresto.R;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RestaurantsActivity_ViewBinding implements Unbinder {
  private RestaurantsActivity target;

  private View view7f080046;

  private View view7f080045;

  private TextWatcher view7f080045TextWatcher;

  @UiThread
  public RestaurantsActivity_ViewBinding(RestaurantsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RestaurantsActivity_ViewBinding(final RestaurantsActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.activity_restaurants_gridView, "field 'gridView' and method 'onRestaurantItemClick'");
    target.gridView = Utils.castView(view, R.id.activity_restaurants_gridView, "field 'gridView'", GridView.class);
    view7f080046 = view;
    ((AdapterView<?>) view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> p0, View p1, int p2, long p3) {
        target.onRestaurantItemClick(p2);
      }
    });
    view = Utils.findRequiredView(source, R.id.activity_restaurants_editText, "field 'editText' and method 'searchForRestaurants'");
    target.editText = Utils.castView(view, R.id.activity_restaurants_editText, "field 'editText'", EditText.class);
    view7f080045 = view;
    view7f080045TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.searchForRestaurants(p0);
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view7f080045TextWatcher);
  }

  @Override
  @CallSuper
  public void unbind() {
    RestaurantsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.gridView = null;
    target.editText = null;

    ((AdapterView<?>) view7f080046).setOnItemClickListener(null);
    view7f080046 = null;
    ((TextView) view7f080045).removeTextChangedListener(view7f080045TextWatcher);
    view7f080045TextWatcher = null;
    view7f080045 = null;
  }
}
