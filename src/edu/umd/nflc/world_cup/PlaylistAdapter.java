package edu.umd.nflc.world_cup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PlaylistAdapter extends BaseAdapter {

	private final String[] titles;
	private final ChantPlayer chants;
	private final LayoutInflater inflater;
	private final int limit;

	public PlaylistAdapter(Context context, String[] titles, ChantPlayer chants, int limit) {
		this.titles = titles;
		this.chants = chants;
		this.inflater = LayoutInflater.from(context);
		this.limit = limit;
	}

	@Override
	public int getCount() {
		return titles.length;
	}

	@Override
	public Object getItem(int position) {
		return titles[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_item_compound, null);

		TextView label = (TextView) convertView.findViewById(R.id.text);
		label.setText(titles[position]);

		if (position < limit) {
			View loading = convertView.findViewById(R.id.loading);
			View error = convertView.findViewById(R.id.error);
			View playButton = convertView.findViewById(R.id.play);
			
			playButton.setOnClickListener(chants);
			playButton.setTag(position);

			chants.prepare(position, loading, playButton, error);
		}

		return convertView;
	}

}