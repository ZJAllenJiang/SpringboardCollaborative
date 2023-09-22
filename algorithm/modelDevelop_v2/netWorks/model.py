from tensorflow.keras.layers import Dense, Input, concatenate
from tensorflow.keras.models import Model


class NetWorks:

    def baseEncoder(self,inputShape):

        ini = Input(inputShape)
        x = Dense(64, 'relu')(ini)
        x = Dense(32, 'relu')(x)
        out = Dense(16, activation='relu')(x)
        return Model(ini, out)

    def tripletNet(self,inputShape, baseEncoder):
        # input_shape is（3，784）
        input = Input(shape=inputShape)
        input_anchor = input[:, 0]
        input_positive = input[:, 1]
        input_negative = input[:, 2]

        # ------------------------------------------#
        #   pass the three inputs to the backone feature extraction network
        # ------------------------------------------#
        encoded_anchor = baseEncoder(input_anchor)
        encoded_positive = baseEncoder(input_positive)
        encoded_negative = baseEncoder(input_negative)

        # connect the vector
        merged_vector = concatenate([encoded_anchor, encoded_positive, encoded_negative],
                                    axis=-1)
        return Model(input, merged_vector)