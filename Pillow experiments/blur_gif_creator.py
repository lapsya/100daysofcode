import sys

from PIL import Image, ImageFilter

im = Image.open(sys.argv[1])
shrink_param = 0.3
im = im.resize((int(shrink_param * im.size[0]), int(shrink_param * im.size[1])))

blurred_images = []
for radius in range(10):
    blurred_images += [im.filter(ImageFilter.GaussianBlur(radius + 1))]
for radius in range(10, 0, -1):
    blurred_images += [im.filter(ImageFilter.GaussianBlur(radius))]

im.save('{}.gif'.format(sys.argv[1].split('.')[0]), save_all=True, append_images=blurred_images)
