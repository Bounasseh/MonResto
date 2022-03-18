// Generated code from Butter Knife. Do not modify!
package com.example.monresto.activities;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.monresto.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RestaurantActivity_ViewBinding implements Unbinder {
  private RestaurantActivity target;

  private View view7f08005f;

  private View view7f080124;

  private View view7f080090;

  private View view7f0800d3;

  @UiThread
  public RestaurantActivity_ViewBinding(RestaurantActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RestaurantActivity_ViewBinding(final RestaurantActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.call_button, "field 'callButton' and method 'makePhoneCall'");
    target.callButton = Utils.castView(view, R.id.call_button, "field 'callButton'", Button.class);
    view7f08005f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.makePhoneCall();
      }
    });
    view = Utils.findRequiredView(source, R.id.scan_button, "field 'scanButton' and method 'scanQRCode'");
    target.scanButton = Utils.castView(view, R.id.scan_button, "field 'scanButton'", Button.class);
    view7f080124 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.scanQRCode();
      }
    });
    view = Utils.findRequiredView(source, R.id.display_menu_button, "field 'MenuButton' and method 'displayMenu'");
    target.MenuButton = Utils.castView(view, R.id.display_menu_button, "field 'MenuButton'", Button.class);
    view7f080090 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.displayMenu();
      }
    });
    view = Utils.findRequiredView(source, R.id.map_button, "field 'mapButton' and method 'moveToRestaurant'");
    target.mapButton = Utils.castView(view, R.id.map_button, "field 'mapButton'", Button.class);
    view7f0800d3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.moveToRestaurant();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RestaurantActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.callButton = null;
    target.scanButton = null;
    target.MenuButton = null;
    target.mapButton = null;

    view7f08005f.setOnClickListener(null);
    view7f08005f = null;
    view7f080124.setOnClickListener(null);
    view7f080124 = null;
    view7f080090.setOnClickListener(null);
    view7f080090 = null;
    view7f0800d3.setOnClickListener(null);
    view7f0800d3 = null;
  }
}
