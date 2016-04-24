package com.imli.demo.ViewModels;

/**
 * Created by developers on 23/04/16.
 */
public class CSVFileGetterSetter {

    private String songName;
    private String albumName;
    private String artistName;
    private int albumTaken;
    private int artistTaken;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getAlbumTaken() {
        return albumTaken;
    }

    public void setAlbumTaken(int albumTaken) {
        this.albumTaken = albumTaken;
    }

    public int getArtistTaken() {
        return artistTaken;
    }

    public void setArtistTaken(int artistTaken) {
        this.artistTaken = artistTaken;
    }
}
