package com.webactiviti.gestionfragment.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fr.afpa.GestionFragment.R;
import com.webactiviti.gestionfragment.model.beans.Fields;

// Fragment de la page principale
public class FragmentMain extends Fragment implements FieldAdapter.OnFieldsListener {


    public final static String ARRAY_FIELDS_KEY = "ARRAY_FIELDS_KEY";
    private RecyclerView rcvFragment;


    // donnée récupéré
    private ArrayList<Fields> fields;
    //Outil
    private FieldAdapter fieldAdapter;


    private Fields OneField;
    private OnFragmentInteractionListener mListener;

    public FragmentMain() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentMain newInstance(ArrayList<Fields> fields) {
        FragmentMain fragment = new FragmentMain();
        Bundle args = new Bundle();
        // passage des données en mode sérialisable
        args.putSerializable(ARRAY_FIELDS_KEY ,  fields);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args.containsKey(ARRAY_FIELDS_KEY)){
            this.fields = (ArrayList<Fields>) args.getSerializable(ARRAY_FIELDS_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_main, container, false);

        rcvFragment = (RecyclerView) view.findViewById(R.id.rcvFragment);
        fieldAdapter = new FieldAdapter(this.fields, this);
        rcvFragment.setLayoutManager(new LinearLayoutManager(null));
        rcvFragment.setAdapter(fieldAdapter);
        return view ;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Fields oneField) {
        if (mListener != null) {
            mListener.onFragmentInteraction(oneField);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // gestion click
    @Override
    public void onClick(Fields oneField) {
        onButtonPressed(oneField);
    }

    // interface du fragment pour récupération
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Fields fields);
    }
}
