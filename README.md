# PhotoGalleryTask
### Prodedure to add the dependency in your android project is :
1. Add this line in build.gradle(Project:project)   :       
   maven { url 'https://jitpack.io' }
```
    allprojects {
        repositories {
            google()
            jcenter()
            maven { url 'https://jitpack.io' }
        }
    }
```
2. Add this dependency in you build.gradle(app:module)
```
implementation 'com.github.Pillai-Ajay:PhotoGalleryTask:1.2.2'
```
### Features of this library :
1. It displays images in recycler view in vertical orientation.
2. It opens the same image in another screen which you are clicked in recyclerview.
3. You can scroll the images in left to right and right to left direction by using arrow buttons as well as swiping the screeen.
4. You can zoom in and zoom out the opened image.
5. Slideshow of images feature is also available.

## Use the below code in program to use the features

```
    PhotoGallery.with(MainActivity.this,stringOfArrayList)
                .setSeconds(5)         
                .setSpanCount(1)       
                .show();
```
```
    with() - Used to pass Activity and String of array list ArrayList<String>.
```
```
   setSpanCount() - Used to set span count for recycler view (Default value : 1)
```
```
   setSeconds() - Used to specify the seconds for delay in slide show (Default value : 5)
```
