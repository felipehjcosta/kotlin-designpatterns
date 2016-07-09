package com.felipecosta.designpatterns.structural.adapter

interface MediaPlayer {
    fun play(audioType: String, fileName: String)
}

interface AdvancedMediaPlayer {
    fun playVlc(fileName: String)

    fun playMp4(fileName: String)
}

class VlcPlayer : AdvancedMediaPlayer {
    override fun playVlc(fileName: String) {
        println("Playing vlc file. Name: $fileName")
    }

    override fun playMp4(fileName: String) {
        throw UnsupportedOperationException("not implemented")
    }
}

class Mp4Player : AdvancedMediaPlayer {
    override fun playVlc(fileName: String) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun playMp4(fileName: String) {
        println("Playing mp4 file. Name: $fileName")
    }
}

class MediaPlayerAdapter(val vlcPlayer: AdvancedMediaPlayer, val mp4Player: AdvancedMediaPlayer) : MediaPlayer {

    override fun play(audioType: String, fileName: String) {

        when (audioType) {
            "vlc" -> vlcPlayer.playVlc(fileName)
            "mp4" -> mp4Player.playMp4(fileName)
            else -> println("Audio file $audioType not supported")
        }
    }
}

class AudioPlayer(val mediaPlayer: MediaPlayer) : MediaPlayer {

    override fun play(audioType: String, fileName: String) {
        mediaPlayer.play(audioType, fileName)
    }
}

fun main(args: Array<String>) {
    val mediaPlayerAdapter = MediaPlayerAdapter(VlcPlayer(), Mp4Player())

    val audioPlayer: MediaPlayer = AudioPlayer(mediaPlayerAdapter)

    audioPlayer.play("mp3", "beyond the horizon.mp3")
    audioPlayer.play("mp4", "alone.mp4")
    audioPlayer.play("vlc", "far far away.vlc")
    audioPlayer.play("avi", "mind me.avi")
}
