package com.webactiviti.gestionfragment.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.webactiviti.GestionFragment.R;
import com.webactiviti.gestionfragment.model.beans.Fields;

import org.jetbrains.annotations.NotNull;


public class FragmentDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    //Composants graphiques
    private TextView ttvTitre, ttvDetail;

    public FragmentDetail() {
        // Required empty public constructor
    }


    public static FragmentDetail newInstance(Fields fields) {
        FragmentDetail fragment = new FragmentDetail();
        Bundle args = new Bundle();

        // passage des paramètres Titres et détail manifestation
        args.putString(ARG_PARAM1, fields.getNom_de_la_manifestation());
        args.putString(ARG_PARAM2, fields.getDescriptif_long());
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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Récupère le view pour le find
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ttvTitre  = view.findViewById(R.id.ttvTitreFrag);
        ttvTitre.setText(mParam1);

        ttvDetail = view.findViewById(R.id.ttvDetailFrag);
        ttvDetail.setText(mParam2);
        // Inflate the layout for this fragment
        return view ;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
