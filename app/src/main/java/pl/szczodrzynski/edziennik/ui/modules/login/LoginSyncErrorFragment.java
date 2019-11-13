package pl.szczodrzynski.edziennik.ui.modules.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import pl.szczodrzynski.edziennik.App;
import pl.szczodrzynski.edziennik.R;
import pl.szczodrzynski.edziennik.databinding.FragmentLoginSyncErrorBinding;

public class LoginSyncErrorFragment extends Fragment {

    private App app;
    private NavController nav;
    private FragmentLoginSyncErrorBinding b;
    private static final String TAG = "LoginSyncError";

    public LoginSyncErrorFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getActivity() != null) {
            app = (App) getActivity().getApplicationContext();
            nav = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        }
        else {
            return null;
        }
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_login_sync_error, container, false);
        return b.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        assert getContext() != null;
        assert getActivity() != null;

        b.errorDetails.setText(LoginActivity.error == null ? "" : LoginActivity.error.getStringReason(getActivity()));

        b.reportButton.setOnClickListener((v -> {
            // TODO error report activity open here app.apiEdziennik.guiReportError(getActivity(), LoginActivity.error, null);
        }));

        b.nextButton.setOnClickListener((v -> {
            nav.navigate(R.id.loginFinishFragment, null, LoginActivity.navOptions);
        }));
    }
}

