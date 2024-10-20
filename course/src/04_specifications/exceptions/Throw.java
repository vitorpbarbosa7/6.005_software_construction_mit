static void analyzeEverything() {
    analyzeThingsInOrder();
}

static void analyzeThingsInOrder() {
    try {
        for (Thing t : ALL_THE_THINGS) {
            analyzeOneThing(t);
        }
        // assumes analyzeOneThing have checked Exception
    } catch (AnalysisException e) {
        return;
    }
}

// ok, it really has checked type exception 
static void analyzeOneThing(Thing t) throws AnalysisException {
    // ...
    // ... maybe go off the end of an array
    // ...
}
