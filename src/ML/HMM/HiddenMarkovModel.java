package ML.HMM;


import javafx.util.Pair;

import java.util.*;

public class HiddenMarkovModel {
    private int numberOfStates;
    private int numberOfObservations;
    private Vector<String> states;
    private Vector<String> observations;
    private Hashtable<String, Double> initialProbabilities;
    private Hashtable<Pair<String, String>, Double> transitionMatrix;
    private Hashtable<Pair<String, String>, Double> emissionMatrix;

    /**
     * A constructor that initialize the class attributes
     * @param states A Vector<String> that is the states of the model
     * @param observations  A Vector<String> that is the observations of the model
     * @param initialProbabilities A Hashtable that is the initial probability vector of the states
     * @param transitionMatrix A Hashtable the transition matrix between the states
     * @param emissionMatrix A Hashtable that is the emission matrix between the states and the observations
     */

    public HiddenMarkovModel(Vector<String> states, Vector<String> observations, Hashtable<String, Double> initialProbabilities, Hashtable<Pair<String, String>, Double> transitionMatrix, Hashtable<Pair<String, String>, Double> emissionMatrix) throws Exception {
        this.states = states;
        this.numberOfStates = states.size();
        this.observations = observations;
        this.numberOfObservations = observations.size();

        this.initialProbabilities = initialProbabilities;
        if (!this.validateInitialProbability(initialProbabilities))
            throw new Exception("Initial Probabilities sum must be equal 1.0");
        if (!this.validateInitialProbabilitiesAndStatesSizes(states.size(), initialProbabilities.size()))
            throw new Exception("States size and Initial Probabilities size must be equal");

        this.transitionMatrix = transitionMatrix;
        if (!this.validateTransitionMatrix(transitionMatrix, states))
            throw new Exception("Check the transition matrix elements");

        this.emissionMatrix = emissionMatrix;
        if (!this.validateEmissionMatrix(emissionMatrix, states, observations))
            throw new Exception("Check the emission matrix elements");
    }

    public HiddenMarkovModel(String filepath) {

    }

    /**
     *
     * @param initialProbabilities A hashtable that is the initial probability vector of the states
     * @return [True/False] which specifies if the vector elements are logically right or not
     */

    private boolean validateInitialProbability(Hashtable<String, Double> initialProbabilities) {
        return Validator.getInstance().summationIsOne(initialProbabilities);
    }

    /**
     *
     * @param statesSize int that is the states size
     * @param initialProbabilitiesSize int the initial probabilities vector size
     * @return [True/False] which specifies if the sizes are matched or not
     */

    private boolean validateInitialProbabilitiesAndStatesSizes(int statesSize, int initialProbabilitiesSize) {
        return Validator.getInstance().isEqualSize(statesSize, initialProbabilitiesSize);
    }

    /**
     *
     * @param transitionMatrix A Hashtable that is the transition matrix between the states
     * @param states A Vector that is the states of the model
     * @return [True/False] which specifies if the matrix elements are logically right or not
     */

    private boolean validateTransitionMatrix(Hashtable<Pair<String, String>, Double> transitionMatrix, Vector<String> states) {
        return Validator.getInstance().isValidTransitionMatrix(transitionMatrix, states);
    }

    /**
     *
     * @param emissionMatrix A Hashtable that is the emission matrix between the states and the observations
     * @param states A Vector that is the states of the model
     * @param observations A Vector that is the model observations
     * @return [True/False] True/False which specifies if the matrix elements are logically right or not
     */

    private boolean validateEmissionMatrix(Hashtable<Pair<String, String>, Double> emissionMatrix, Vector<String> states, Vector<String> observations) {
        return Validator.getInstance().isValidEmissionMatrix(emissionMatrix, states, observations);
    }

    /**
     * Get the number of states in the model
     * @return An integer that specifies the number of states in the model
     */

    public int getNumberOfStates() {
        return numberOfStates;
    }

    /**
     * Set the number of states in the model
     * @param numberOfStates integer
     */

    public void setNumberOfStates(int numberOfStates) {
        this.numberOfStates = numberOfStates;
    }

    /**
     * Get the number of observations in the model
     * @return An integer that specifies the number of observations in the model
     */

    public int getNumberOfObservations() {
        return numberOfObservations;
    }

    /**
     * Set the number of observations in the model
     * @param numberOfObservations An integer that specifies the number of observations in the model
     */

    public void setNumberOfObservations(int numberOfObservations) {
        this.numberOfObservations = numberOfObservations;
    }

    /**
     * Get the initial probability vector of the states
     * @return Hashtable that is the initial probability vector of the states
     */

    public Hashtable<String, Double> getInitialProbabilities() {
        return initialProbabilities;
    }

    /**
     * Set the initial probability vector of the states
     * @param initialProbabilities Hashtable that is the initial probability vector of the states
     */

    public void setInitialProbabilities(Hashtable<String, Double> initialProbabilities) {
        this.initialProbabilities = initialProbabilities;
    }

    /**
     * Get the transition matrix between the states
     * @return Hashtable that is the transition matrix between the states
     */

    public Hashtable<Pair<String, String>, Double> getTransitionMatrix() {
        return transitionMatrix;
    }

    /**
     * Set the transition matrix between the states
     * @param transitionMatrix Hashtable that is the transition matrix between the states
     */

    public void setTransitionMatrix(Hashtable<Pair<String, String>, Double> transitionMatrix) {
        this.transitionMatrix = transitionMatrix;
    }

    /**
     * Get the emission matrix between the states and the observations
     * @return Hashtable that is the emission matrix between the states and the observations
     */

    public Hashtable<Pair<String, String>, Double> getEmissionMatrix() {
        return emissionMatrix;
    }

    /**
     * Set the emission matrix between the states and the observations
     * @param emissionMatrix Hashtable that is the emission matrix between the states and the observations
     */

    public void setEmissionMatrix(Hashtable<Pair<String, String>, Double> emissionMatrix) {
        this.emissionMatrix = emissionMatrix;
    }

    /**
     *
     * @param firstState A string that is a state in the model
     * @param secondState A string that is a state in the model
     * @return A Double that is the transition value between the 2 states
     */

    public Double getTransitionValue(String firstState, String secondState) {
        return this.transitionMatrix.get(new Pair<String, String>(firstState, secondState));
    }

    /**
     *
     * @param state A string that is a state in the model
     * @param observation A string that is an observation in the model
     * @return A Double that is the value of the emission between the state and the observation
     */

    public Double getEmissionValue(String state, String observation) {
        return this.emissionMatrix.get(new Pair<String, String>(state, observation));
    }

    /**
     *
     * @param state A string that is a state in the model
     * @return A Double that is the initial probability value of the state
     */

    public Double getInitialProbability(String state) {
        return this.initialProbabilities.get(state);
    }



}