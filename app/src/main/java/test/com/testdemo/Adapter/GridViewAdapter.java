package test.com.testdemo.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import test.com.testdemo.Bean.ApiData;
import test.com.testdemo.R;

public class GridViewAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private LruCache<String, Bitmap> memoryCache;
    List<ApiData> mItemList;
    ImageView imageView;

    public GridViewAdapter(Context context, List<ApiData> itemList)
    {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemList = itemList;
    }

    @Override
    public int getCount()
    {
        //取得 GridView 列表 Item 的數量
        return mItemList.size();
    }

    @Override
    public Object getItem(int position)
    {
        //取得 GridView列表於 position 位置上的 Item
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        //取得 GridView 列表於 position 位置上的 Item 的 ID
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = mLayoutInflater.inflate(R.layout.item_gridview, parent, false);

        TextView id = (TextView) v.findViewById(R.id.id);
        TextView title = (TextView) v.findViewById(R.id.title);
        imageView = (ImageView) v.findViewById(R.id.imageView);

        id.setText(mItemList.get(position).getId());
        title.setText(mItemList.get(position).getTitle());

        new DownloadImageTask(imageView)
                .execute(mItemList.get(position).getThumbnailUrl());

        return v;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
