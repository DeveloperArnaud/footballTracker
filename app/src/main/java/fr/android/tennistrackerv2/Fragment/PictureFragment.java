package fr.android.tennistrackerv2.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import java.util.List;

import fr.android.tennistrackerv2.Adapter.ImageUploadAdapter;
import fr.android.tennistrackerv2.Callback.ISendDataFragment;
import fr.android.tennistrackerv2.Model.Upload;
import fr.android.tennistrackerv2.R;
import fr.android.tennistrackerv2.ViewModel.UploadViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PictureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PictureFragment extends Fragment {
    private RecyclerView recyclerView;
    private ImageUploadAdapter imageUploadAdapter;
    private List<Upload> uploads;
    private UploadViewModel uploadViewModel;
    SwipeRefreshLayout swipeRefreshLayout;
    ImageButton btnDownload;
    ISendDataFragment iSendDataFragment;
    MenuItem item;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PictureFragment(ISendDataFragment iSendDataFragment) {
        // Required empty public constructor
        this.iSendDataFragment = iSendDataFragment;
    }

    public PictureFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PictureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PictureFragment newInstance(String param1, String param2) {
        PictureFragment fragment = new PictureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_picture, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_img);

        uploadViewModel = new ViewModelProvider(this).get(UploadViewModel.class);
        uploadViewModel.getUploadList().observe(getViewLifecycleOwner(), uploadsFirebase -> {
            //uploads = uploadsFirebase;
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            imageUploadAdapter = new ImageUploadAdapter(getContext(), uploadsFirebase);
            recyclerView.setAdapter(imageUploadAdapter);
            imageUploadAdapter.notifyDataSetChanged();

        });


        System.out.println(uploads);

        return view;
    }

    public void setUploads(List<Upload> uploadList) {
        this.uploads = uploadList;
    }

    public List<Upload> getUploads() {
        return uploads;
    }


}