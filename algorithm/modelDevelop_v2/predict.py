import tensorflow as tf
from sklearn.preprocessing import StandardScaler,MinMaxScaler
from dataProcessor import dataStandardize,dataLoad
from train import getTrainCentorVectors
import tensorflow.keras.backend as K
import pickle
import numpy as np



def predict(data,baseEncoder,trainCentorVectors):
    scaler = pickle.load(open('resource/scaler.pkl', 'rb'))
    data = np.array([data])
    data = scaler.transform(data)
    res = baseEncoder(data)

    distList = []
    for i in range(len(trainCentorVectors)):
        distList.append([K.sum(K.square(res[0] - trainCentorVectors[i]), axis=0),i])

    distList.sort(key=lambda x: (x[0]))
    print(distList)

if __name__ == "__main__" :

    baseEncoder = tf.keras.models.load_model('models/1')
    classArrayForTrain = np.load('resource/classArrayForTrain.npy', allow_pickle=True)
    trainCentorVectors = getTrainCentorVectors(classArrayForTrain, baseEncoder)

    datas, labels =  dataLoad()
    print(labels[88])
    predict(datas[88],baseEncoder,trainCentorVectors)








