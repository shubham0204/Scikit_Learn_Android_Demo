# Using scikit-learn Models In Android Applications

This project demonstrates the use of a scikit-learn model in an Android app using ONNX as a bridge between both the frameworks.

![Demo of the app](images/sklearn_model_demo_android.gif)

## Getting Started

1. Clone this repository and open the resulting project in Android Studio,

```
>> git clone https://github.com/shubham0204/Scikit_Learn_Android_Demo
```

2. Read the blog [Deploying Scikit-Learn Models In Android Apps With ONNX](https://towardsdatascience.com/deploying-scikit-learn-models-in-android-apps-with-onnx-b3adabe16bab) to follow the procedure for your model. The code included in this blog is available as an [Google Colab notebook](https://colab.research.google.com/github/shubham0204/Google_Colab_Notebooks/blob/main/Sklearn_to_ONNX_Conversion.ipynb).

3. Place your `.ort` model in the `app/src/main/res/raw` folder.