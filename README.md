# KIA


Machine Learning

/-What we did overall
KIA used Artificial Neural Network (ANN) approach to create a machine learning model using Tensorflow Keras library. By building the Convolutional Neural Network (CNN) layers from scratch and collecting the images we needed to train the dataset, we manage to create an image classification model with the ability to classify 7 categories (Anggur, Apel, Brokoli, Jeruk, Pisang, Tahu, Wortel) with the average of 75% accuracy.
*note: we created the label in Bahasa Indonesia because our priority is to deploy in Indonesia. Label in English (Grape, Apple, Broccoli, Orange, Banana, Tofu, Carot).

/-What we did for dataset
For the dataset itself, we collect them from the internet. Each category has about 100+ images. The kind of images we use is as long as the human eyes can see the designated object, not constrain on single object or multiple objects inside a image, with most random background as possible but we avoid the one that are still in the trees or in the garden, raw or cooked, and some are sliced open or already being cut. We focus more on what occasion for moms with babies will prepare the food. For preprocessing the dataset, we mostly just select the images selectively from the boundaries we mention before.

/-What we did for ML model
We create the model using google Colaboratory notebook to utilize the free GPU usage for faster training time. We upload the dataset in a zip format to a google drive then mount the drive to our collab. When the drive is mounted, we load the dataset and unzip the zip file. The dataset now is ready to use, first we did the image data augmentation followed with splitting the dataset to 80/20 split (80% training and 20% testing) using ImageDataGenerator preprocessing method from Keras. Next we use data generator for the training_generator and testing_generator as our training and testing data which also did some image resizing for each images to 224x224 (224, 224) pixels and shuffle them before we feed the data to the ML. We also specify that we wanted to make a categorical class mode, with rgb as color mode.
Now we are going to create a CNN model from scratch. We choose to make a sequential model with 4 convolution layers with the input image as 224x224 pixels while also using rgb color mode (224, 224, 3), each followed with a single max pooling layer. Then we add a dropout layer with 0.5 parameter value or know as dropping out 50% from the total of neurons to reduce overfitting. The we add flatten layer to feed fo the next layers which is, first dense layer with 512 neurons follow with the output layer of dense with number of neurons as much as the number of categories which is 7 neurons and activation softmax to return value of 1 to the category with highest probability. Next we compile it with categorical_crossentropy loss, Adam optimizer, and using accuracy as the metrics we wanted to monitor.
The training does not require too much time because we only use 20 epochs and we also utilize the free GPU. We specify the epochs to just 20 because after 20 epochs we doesn't really see much improvement from what the model learnt. The training result is about 85% average on training data and 75% average on testing data. We also visualize the training result using matplotlib to monitor the training result easily. Next we added the part to validate the mode by inserting new images that model never seen before. The final result is satisfying enough for us but definitely still needs improvement.

/-What can we see from overall result and we tried before
We start from 2 categories and add 1 category at a time when the current labels result is ok. We monitor that each time we add a new category, overall accuracy is reduced and more the model is prone to overfitting. We tried to add 10 categories but due to time constraints and our lack of practice experience only ended up with 7 categories. We also wanted to make an Object Detection model by trying first with 3 categories and ended up with only 30% accuracy. Another thing we experience while creating an ML model is collecting image data easily can be done from the Open Image dataset. We also tried is transfer learning, but the model is overfitting so bad after adding the third category.

Things can be improved for ML:
1. Switching from image classification to object detection, image classifying is limited to classifying 1 object inside a frame while object detection can detect what object or objects that are found within a frame.
2. Adding more data, currently we lack data for each category. More data can help reduce overfit problems.
3. Increase image resolution, currently we only use 224x224 pixel image while bigger resolution can help convolution to extract feature better.
