// Generated code from Butter Knife. Do not modify!
package com.example.monresto.activities;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.monresto.R;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view7f080189;

  private TextWatcher view7f080189TextWatcher;

  private View view7f080108;

  private TextWatcher view7f080108TextWatcher;

  private View view7f080074;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.checkBox = Utils.findRequiredViewAsType(source, R.id.checkBox, "field 'checkBox'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.username, "field 'usernameEditText' and method 'onUsernameEditTextChanged'");
    target.usernameEditText = Utils.castView(view, R.id.username, "field 'usernameEditText'", EditText.class);
    view7f080189 = view;
    view7f080189TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.onUsernameEditTextChanged();
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view7f080189TextWatcher);
    view = Utils.findRequiredView(source, R.id.password, "field 'passwordEditText' and method 'onPasswordEditTextChanged'");
    target.passwordEditText = Utils.castView(view, R.id.password, "field 'passwordEditText'", EditText.class);
    view7f080108 = view;
    view7f080108TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.onPasswordEditTextChanged();
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view7f080108TextWatcher);
    view = Utils.findRequiredView(source, R.id.connect, "field 'connectButton' and method 'onConnectButtonClicked'");
    target.connectButton = Utils.castView(view, R.id.connect, "field 'connectButton'", Button.class);
    view7f080074 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onConnectButtonClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.checkBox = null;
    target.usernameEditText = null;
    target.passwordEditText = null;
    target.connectButton = null;

    ((TextView) view7f080189).removeTextChangedListener(view7f080189TextWatcher);
    view7f080189TextWatcher = null;
    view7f080189 = null;
    ((TextView) view7f080108).removeTextChangedListener(view7f080108TextWatcher);
    view7f080108TextWatcher = null;
    view7f080108 = null;
    view7f080074.setOnClickListener(null);
    view7f080074 = null;
  }
}
