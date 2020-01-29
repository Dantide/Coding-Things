function [xx,tt] = beatsig (Amp, fc, fDelta, phic, phiDelta)
  tStart = 0; %-- starting time (secs)
  tStop = 5; %-- ending time (secs)
  fSamp = 8000;
  Tsect = tStop-tStart;
  %
  tt = tStart:(1/fSamp):tStop; %-- vector of times
  xx = Amp*cos(2*pi*fc*tt+phic).*cos(2*pi*fDelta*tt+phiDelta);
  % plot(tt,xx);
  % plotspec(xx,fSamp,10000); colorbar, grid on, zoom on
  % 2.1.3 a) C = 2
  plotspec(xx,fSamp,2500); colorbar, grid on, zoom on
  
  % beatsig(10,4,1024,2*pi*rand,2*pi*rand)
  
  % beatsig(10,16,1024,2*pi*rand,2*pi*rand)
  