import tensorflow.keras.backend as K

def triplet_loss(y_true, y_pred, alpha=10):


    total_lenght = y_pred.shape.as_list()[-1]

    anchor = y_pred[:, 0:int(total_lenght * 1 / 3)]  # anchor

    positive = y_pred[:, int(total_lenght * 1 / 3):int(total_lenght * 2 / 3)]  # positive

    negative = y_pred[:, int(total_lenght * 2 / 3):int(total_lenght * 3 / 3)]  # negative

    # distance between the anchor and the positive
    pos_dist = K.sum(K.square(anchor - positive), axis=1)  # l2

    # distance between the anchor and the negative
    neg_dist = K.sum(K.square(anchor - negative), axis=1)

    # compute loss
    basic_loss = pos_dist - neg_dist + alpha
    loss = K.maximum(basic_loss, 0.0)
    loss = K.mean(loss, axis=0, keepdims=False)

    return loss