package com.droidcoding.github.ui.fragment;

import android.support.v4.app.Fragment;
import com.droidcoding.github.MainActivity;

/**
 * Created by Donglua on 16/1/24.
 */
public class NavBaseFragment extends Fragment {

  public MainActivity getMainActivity() {
    return (MainActivity) getActivity();
  }
}
