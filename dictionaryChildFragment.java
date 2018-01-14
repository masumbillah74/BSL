package com.example.Fazlay_Rabbi.banglaSignLanguage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.Fazlay_Rabbi.camerawork_signlanguage.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/**
 * Created by Fazlay_Rabbi on 11/14/2017.
 */


public class dictionaryChildFragment extends Fragment
{
    ListView dictionaryListView;
    TextView wordName;

    ArrayList<Content> listOfSystemDefinedContent;


    @Override
    public void onCreate(Bundle savedData) {

        super.onCreate(savedData);
        listOfSystemDefinedContent = getContent("/MyDataSet/systemContent","/systemContentMapper.txt");
        ArrayList<Content> listOfUserDefinedContent = getContent("/MyDataSet","/mapper.txt");
        listOfSystemDefinedContent.addAll(listOfUserDefinedContent);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.dictionary_child_fragment_1, container, false);

        dictionaryListView = v.findViewById(R.id.dictionaryListView);
        wordName = v.findViewById(R.id.wordName);
        dictionaryListView.setAdapter(new ListAdapter1(getActivity(), listOfSystemDefinedContent));
        
        dictionaryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                int itemposition =position;
                Content content = (Content) dictionaryListView.getItemAtPosition(position);
                wordName.setText(content.getGestureMeaning());
            }
        });
        return v;
    }


    private ArrayList<Content> getContent(String directory, String mapperFileName)
    {
        HashMap<String, String> gestureAndWordMapper = getMapperOfGesturefileAndWord(directory,mapperFileName);

        ArrayList<Content> listOfContent = new ArrayList<Content>();
        Content content;
        File path =new File(Environment.getExternalStorageDirectory() + directory);

        for ( String key : gestureAndWordMapper.keySet() )
        {
            String filename = key+".jpg";
            File file = new File(path, filename);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            content = new Content();
            content.setGestureImage(bitmap);

            String meaning= gestureAndWordMapper.get(key);
            content.setGestureMeaning(meaning);
            listOfContent.add(content);
        }

        return listOfContent;
    }

    private HashMap<String, String> getMapperOfGesturefileAndWord(String directory,String fileName)
    {
        HashMap<String, String> gestureAndWordMapper = new HashMap<String, String>();
        Scanner scanner = null;
        try
        {
            String storeFolderName = Environment.getExternalStorageDirectory() + directory;
            scanner = new Scanner(new File(storeFolderName+fileName));
        }
        catch (FileNotFoundException e){}

        scanner.useDelimiter(",");
        while(scanner.hasNext())
        {
            String imageFileName = scanner.next();
            String word = scanner.next();
            gestureAndWordMapper.put(imageFileName,word);
        }
        scanner.close();

        return gestureAndWordMapper;
    }

    public static dictionaryChildFragment newInstance(String text)
    {
        dictionaryChildFragment f = new dictionaryChildFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }
}

class Content
{
    private Bitmap gestureImage;
    private String gestureMeaning;

    public Bitmap getGestureImage() {
        return gestureImage;
    }

    public String getGestureMeaning() {
        return gestureMeaning;
    }

    public void setGestureImage(Bitmap gestureImage) {
        this.gestureImage = gestureImage;
    }

    public void setGestureMeaning(String gestureMeaning) {
        this.gestureMeaning = gestureMeaning;
    }

}

class ListAdapter1 extends BaseAdapter
{
    private static ArrayList<Content> contentList;

    private LayoutInflater mInflater;

    public ListAdapter1(Context photosFragment, ArrayList<Content> results){
        contentList = results;
        mInflater = LayoutInflater.from(photosFragment);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return contentList.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return contentList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.dictionary_child_fragment_1_listcontent, null);
            holder = new ViewHolder();
            holder.gestureImage = (ImageView) convertView.findViewById(R.id.gestureImage);
            holder.gestureMeaning = (TextView) convertView.findViewById(R.id.gestureMeaning);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.gestureMeaning.setText(contentList.get(position).getGestureMeaning());
        holder.gestureImage.setImageBitmap(contentList.get(position).getGestureImage());

        return convertView;
    }

    static class ViewHolder{
        TextView gestureMeaning;
        ImageView gestureImage;
    }
}


