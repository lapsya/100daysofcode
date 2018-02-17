"""
1st argument identifies YouTube video link
2nd and 3rd are timestamps of start and end
4th identifies clip fade effect:
    * None specified: no effect
    * 'symmetric' : symmetric loop
    * 'smooth' : smooth fade "animation" (works best with barely moving scenes)

"""

import sys

from pytube import YouTube
import moviepy.editor as mpy

FADE_FX_MODES = ['symmetric', 'smooth', 'none']


def time_symetrize(clip):
    return mpy.concatenate([clip, clip.fx(mpy.vfx.time_mirror)])

def make_gif(filename, start, stop, resize=0.3, fade_fx=None):
    if fade_fx == 'symmetric':
        clip = (mpy.VideoFileClip(filename).subclip(start, stop).resize(resize).fx(time_symetrize))

    elif fade_fx == 'smooth':
        clip = (mpy.VideoFileClip(filename).subclip(start, stop).resize(resize))
        fade_duration = clip.duration / 2

        clip = clip.crossfadein(fade_duration)
        clip = (mpy.CompositeVideoClip([clip,
                                        clip.set_start(fade_duration),
                                        clip.set_start(2 * fade_duration)])
                        .subclip(fade_duration, fade_duration + clip.duration))

    else:
        clip = (mpy.VideoFileClip(filename).subclip(start, stop).resize(resize))


    clip.write_gif(filename.split('.')[0] + '.gif')



# read parameters from command line
link = sys.argv[1]
start = tuple(map(float, sys.argv[2].split(':')))
stop = tuple(map(float, sys.argv[3].split(':')))

try:
    fade_fx = sys.argv[4]
    if fade_fx not in FADE_FX_MODES:
        raise ValueError('4th argument identifies fade effect and has to be one of {{"{}"}} or not specified'
                            .format('", "'.join(FADE_FX_MODES)))
except IndexError:
    fade_fx = 'none'

# create YouTube video stream
vid = YouTube(link)

vid_name = vid.title.replace("'", '').replace('|', '').replace('.', '')
vid_stream = vid.streams.filter(file_extension='mp4').first()


# download the video if necessary and create a gif
try:
    make_gif(vid_name + '.mp4', start, stop, fade_fx=fade_fx)
except OSError:
    vid_stream.download(filename=vid_name)
    make_gif(vid_name + '.mp4', start, stop, fade_fx=fade_fx)
