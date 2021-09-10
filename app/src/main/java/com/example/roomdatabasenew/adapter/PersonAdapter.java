package com.example.roomdatabasenew.adapter;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.roomdatabasenew.MainActivity;
import com.example.roomdatabasenew.R;
import com.example.roomdatabasenew.entity.Person;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    private List<Person> allPerson;
    private MainActivity mainActivity;

    public PersonAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_data_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setdata(holder, allPerson.get(position));
    }

    @Override
    public int getItemCount() {
        if (allPerson != null && allPerson.size() > 0) {
            return allPerson.size();
        } else {
            return 0;
        }
    }

    public void setData(List<Person> allPerson) {
        this.allPerson = allPerson;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView fr_name, lt_name, prId;
        public ImageView iv_delete, iv_edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fr_name = itemView.findViewById(R.id.fr_name);
            lt_name = itemView.findViewById(R.id.lt_name);
            iv_delete = itemView.findViewById(R.id.iv_delete);
            iv_edit = itemView.findViewById(R.id.iv_edit);
            prId = itemView.findViewById(R.id.prId);
        }

        public void setdata(MyViewHolder holder, Person person) {
            holder.fr_name.setText(person.getFirstname());
            holder.lt_name.setText(person.getLastName());
            holder.prId.setText(person.getUid() + "");

            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainActivity.deletePerson(allPerson.get(holder.getAdapterPosition()));
                }
            });


            holder.iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog(mainActivity, allPerson.get(holder.getAdapterPosition()));
                    //mainActivity.deletePerson(allPerson.get(holder.getAdapterPosition()));
                }
            });

        }
    }


    public void showDialog(MainActivity mainActivity, Person person) {
        final Dialog dialog = new Dialog(mainActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.update_dialog);

        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //     dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView prId = dialog.findViewById(R.id.prId);
        TextInputEditText f_name = dialog.findViewById(R.id.f_name);
        TextInputEditText l_name = dialog.findViewById(R.id.l_name);
        Button button_save = dialog.findViewById(R.id.button_save);

        f_name.setText(person.getFirstname());
        l_name.setText(person.getLastName());
        prId.setText(person.getUid() + "");

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = f_name.getEditableText().toString();
                String lstNme = l_name.getEditableText().toString();
                Person person1 = new Person();
                person1.setUid(person.getUid());
                person1.setFirstname(fName);
                person1.setLastName(lstNme);
                mainActivity.updatePerson(person1);
                dialog.dismiss();
            }
        });

        dialog.show();

    }



}
