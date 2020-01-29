function chirpsyn(fzero,fend,tStop)
  % fSamp = 8000; %-Number of time samples per second
  fSamp = 4000;
  dt = 1/fSamp;
  tStart = 0;
  tt = tStart:dt:tStop;
  mu = (fend-fzero)/(2 * tStop);
  phi = 2*pi*rand; %-- random phase
  psi = (2*pi*mu*(tt.*tt)) + (2*pi*fzero*tt) + phi;
  %
  % disp(length(tt));
  % disp(length(psi));
  % disp(fSamp * tStop);
  assert(length(psi) == fSamp * tStop + 1);
  %
  cc = real( 7.7*exp(j*psi) );
  soundsc( cc, fSamp ); %-- uncomment to hear the sound
  plotspec( cc+j*1e-12, fSamp, 200 ), colorbar, grid on %-- with negative frequencies
  
  % chirpsyn(2000,-1000,1.5)