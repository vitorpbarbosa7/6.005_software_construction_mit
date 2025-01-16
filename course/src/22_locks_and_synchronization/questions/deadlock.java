
/* ########################### */ 
// Thread 1
synchronized (alpha) {
  // using alpha
  // ...
}

synchronized (gamma) {
  synchronized(beta) {
    // Using beta and gamma
    // ...
    }
}

/* ########################### */ 


/* ########################### */ 
// Thread 2
synchronized (gamma) {
  synchronized (alpha) {
    synchronized (beta) {
      // using alpha, beta & gamma
      // ...
    }
  }
}
/* ########################### */ 


/* ########################### */ 
// Thread 3
synchronized (gamma) {
  synchronized(alpha) {
    // using alpha and gamma
    // ...
  }
}

synchronized (beta)
  synchronized(gamma) {
    // using beta and gamma
    // ...
  }
}
/* ########################### */ 
