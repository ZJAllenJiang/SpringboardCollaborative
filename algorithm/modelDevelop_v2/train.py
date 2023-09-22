import numpy as np

from modelDevelop_v2.netWorks.model import NetWorks
from modelDevelop_v2.netWorks.loss import triplet_loss
from modelDevelop_v2.netWorks.dataGenerator import triplet_generator
from dataProcessor import getDatas
from tensorflow.python.keras import optimizers
import tensorflow as tf
import tensorflow.keras.backend as K
from sklearn.manifold import TSNE
# import matplotlib.pyplot as plt



def train(classArrayForTrain, classArrayForTest, trianDatasNum, testDatasNum,lr=1e-5,batchSize=256,epochs=100,featureLen=784):

    baseEncoder = NetWorks().baseEncoder([featureLen])
    tripletNet = NetWorks().tripletNet([3,featureLen],baseEncoder)


    tripletNet.compile(
        optimizer = optimizers.adam_v2.Adam(learning_rate=lr),
        loss=triplet_loss)

    history=tripletNet.fit_generator(generator=triplet_generator(classArrayForTrain, batchSize),
        steps_per_epoch=trianDatasNum//batchSize,
        validation_data=triplet_generator(classArrayForTest, batchSize),
        validation_steps=testDatasNum//batchSize, epochs=epochs)

    baseEncoder.save('models/1')

    #========get loss trend=========
    # loss = history.history['loss']
    # val_loss = history.history['val_loss']
    #
    # epochs_range = range(epochs)
    #
    # plt.subplot(1, 1, 1)
    # plt.plot(epochs_range, loss, label='Training Loss')
    # plt.plot(epochs_range, val_loss, label='Validation Loss')
    # plt.legend(loc='upper right')
    # plt.title('Training and Validation Loss')
    # plt.show()

def getTrainCentorVectors(classArrayForTrain, baseEncoder):
    trainDatasResCentorVectors = []
    for classData in classArrayForTrain:
        res = baseEncoder(np.array(classData))
        totalNum = len(res)
        res = np.sum(res, axis=0)/totalNum
        trainDatasResCentorVectors.append(res)
    return trainDatasResCentorVectors


def accuracyCalculate(classArrayForTrain,classArrayForTest,baseEncoder):

    trainCentorVectors = getTrainCentorVectors(classArrayForTrain,baseEncoder)
    testResVectors = []
    for classArray in classArrayForTest:
        testResVectors.append(baseEncoder(np.array(classArray)))

    right=0
    total = testDatasNum
    for i in range(len(trainCentorVectors)):
        for testVector in testResVectors[i]:
            distList=[]
            for trainCentorVector in trainCentorVectors:
                distList.append(K.sum(K.square(testVector - trainCentorVector), axis=0))
            current_index = distList.index(min(distList))
            if current_index == i:
                right+=1

    result = right/total
    return result

def make_new_datas_tsne(datas, model):
    new_datas = []
    labels = []

    for i in range(len(datas)):

        new_data = model(np.array(datas[i]))
        for data in new_data:
            new_datas.append(data)
            labels.append(i)

    new_datas = np.array(new_datas)
    labels = np.array(labels)

    return new_datas, labels

# def visualization(classArrayForTrain,classArrayForTest,baseEncoder):
#
#     train_datas_T_one, train_labels_T_one = make_new_datas_tsne(classArrayForTrain, baseEncoder)
#     val_datas_T_one, val_labels_T_one = make_new_datas_tsne(classArrayForTest, baseEncoder)
#
#
#     print(train_datas_T_one.shape)
#     print(train_labels_T_one.shape)
#
#     tsne = TSNE(n_components=2, learning_rate=100).fit_transform(train_datas_T_one)
#
#     print("train_datas_one")
#
#     plt.figure(figsize=(12, 6))
#     plt.subplot(111)
#     plt.scatter(tsne[:, 0], tsne[:, 1], c=train_labels_T_one)
#     plt.show()
#
#     print(val_datas_T_one.shape)
#     print(val_labels_T_one.shape)
#     tsne = TSNE(n_components=2, learning_rate=100).fit_transform(val_datas_T_one)
#
#     print("val_datas_one")
#
#     plt.figure(figsize=(12, 6))
#     plt.subplot(111)
#     plt.scatter(tsne[:, 0], tsne[:, 1], c=val_labels_T_one)
#     plt.show()

if __name__ == "__main__" :

    classArrayForTrain, classArrayForTest, trianDatasNum, testDatasNum = getDatas(persentage=0.8)
    np.save('resource/classArrayForTrain.npy', classArrayForTrain)
    train(classArrayForTrain, classArrayForTest, trianDatasNum, testDatasNum,lr=1e-4,batchSize=2,epochs=700,featureLen=len(classArrayForTrain[0][0]))
    baseEncoder = tf.keras.models.load_model('models/1')
    # visualization(classArrayForTrain, classArrayForTest, baseEncoder)
    print(accuracyCalculate(classArrayForTrain,classArrayForTest,baseEncoder))