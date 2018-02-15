import sys
from PIL import Image, ImageFilter

frames = []
for img_name in sys.argv[1:]:
    frames += [Image.open(img_name)]

frames[0].save('merged.gif', save_all=True, append_images=frames[1:])
