package com.example.bookofrecipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PagerFragment extends Fragment {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String ARGUMENT_ARRAY_LIST = "arg_array_list";

    private int pageNumber;
    private ArrayList<String> recipeImages;

    static PagerFragment newInstance(int page, ArrayList<String> images){
        PagerFragment fragment = new PagerFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        arguments.putStringArrayList(ARGUMENT_ARRAY_LIST, images);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        recipeImages = getArguments().getStringArrayList(ARGUMENT_ARRAY_LIST);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_layout, null);
        ImageView imageView = view.findViewById(R.id.recipe_image_view_details);
        Picasso.get().load(recipeImages.get(pageNumber)).into(imageView);
        TextView textView = view.findViewById(R.id.page_number);
        textView.setText(String.valueOf(pageNumber));
        return view;
    }
}
