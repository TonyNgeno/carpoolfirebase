package com.example.puneet.tempapplication;

/**
 * Created by puneet on 26/2/18.
 */

public class DataModel {
    

        String title;
        String description;
        int id_;
        int image;

        public DataModel(String title, String description, int id_, int image) {
            this.title = title;
            this.description = description;
            this.id_ = id_;
            this.image=image;
        }

        public String getName() {
            return title;
        }

        public String getVersion() {
            return description;
        }

        public int getImage() {
            return image;
        }

        public int getId() {
            return id_;
        }
}

