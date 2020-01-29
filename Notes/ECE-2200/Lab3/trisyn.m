function trisyn(Amp, T, tStop)
  fs = 10000;
  tt=0:(1/fs):tStop;
  qq=rem(tt,T);
  xx=Amp*(abs(qq-(0.5*T))-0.25*T);
  
  % plot two periods
  % disp(length(0:1/fs:T*3));
  % disp(length(xx(1:fs*T*3 + 1)));
  % plot((0:1/fs:T*3),xx(1:fs*T*3 + 1));
  plotspec(xx,fs,500);
  