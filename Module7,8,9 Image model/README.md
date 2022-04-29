# Image Model Project

## About/Overview
Many applications uses color images. A good number of these provide a way to change their appearance in different ways. For example, Instagram has filters that covert a picture into something more interesting. They do this by editing the colors of individual dots in the images (called pixels). 

This Image Model Project incudes some very popular image effects. It allows users to add different effects to their images.

## List of Features

- [x] User can load image file to the program.
- [x] User can set the current image to different filter effects, such as blur, sharpen, sephi, grayscale, dither or mosaic.
- [x] User can save image file with given file name.
- [x] User can load more image files to the program.
- [x] User can apply up to six filter effects to the same image.
- [x] User can see a window and has some intercation with the window.
- [x] User can upload an image file throgh the window.
- [x] User can add filters to the seleted an image file throgh the window.
- [x] User can upload batch-script file throgh the window and produce the instructed performance of the script.
- [x] User can save the image file throgh the window.

## Limitations

- User need to use a .txt file or other inputstream files to add batch-script instructions to the program.
- User need to use a .jpg or .png image file in order to produce the filter effect.
- User cannot type through the keyboard for instruction.
- User must enter a valid file name before save the file.
- This program only works in local environment now.

## How to Run

1) User Open the GuiDriver.java file to start the program
2) User select a valid image file through the menu/open file button or the "choose image" button
3) User can see the image in the window
4) User can upload a batch-script file by clicking the "choose script" button
5) User can see the combobox of 6 different filter effects, and once selected, the image after the filter effect will show on the window


## How to Use the Program

- User must load a valid batch-script file or select a valid image file.
- User must enter a valid filter effect of the following filter effects
        - Blur
        - Sharpen
        - Grayscale
        - Sephia
        - Dither
        - Mosaic with given seeds number
- User must save a valid image file with a file name.

## Examples

With batch-script instructions like this to the program:
"
load snow-mountain.png
blur
blur
save snow-mountain-blurred-2.png
sepia
save snow-mountain-blurred-sepia.png
load side-walk.png
dither
save side-walk-dither.png
load side-walk.png
blur
blur
save side-walk-blurred-2.png
"
The program will produce a blurred image, a image with sepia effect, a image with dither effect, and load another image to produce a blurred image.
