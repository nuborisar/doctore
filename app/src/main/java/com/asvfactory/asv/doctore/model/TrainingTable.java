package com.asvfactory.asv.doctore.model;

import android.transition.Visibility;

import com.asvfactory.asv.doctore.util.FileHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*asv falta:
        * trasladar todo lo q son metodos al ViewModel
        * generar getter/setter exercise Order
        * generar constructor
        * hacer q sea @Bindable, o no y simplemente oneway
*/

//listado con los ficheros de los entrenamientos posibles
public class TrainingTable implements Serializable {
    List<Training> trainings;

    //region getter/setter
    public List<Training> getTrainings() {

        trainings = (List<Training>) FileHelper.readDoctoreFile(Constants.FILE_ALL_TRAININGS);
        if (trainings == null)
            trainings = new ArrayList<>();
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    //endregion
    //region methods
    public void addItem(Training item) {
        if (trainings == null)
            trainings = new ArrayList<>();

        trainings.add(item);
        item.save();
        this.save();

    }

    public void deleteItem(Training trainingItem) {

        for (Exercise item : trainingItem.trainingExercises) {
            trainingItem.deleteItem(item.exerciseID);
        }
        this.trainings.remove(trainingItem);
    }

    private void save() {
        FileHelper.writeDoctoreFile(this,Constants.FILE_ALL_TRAININGS);
    }
    //endregion
}

